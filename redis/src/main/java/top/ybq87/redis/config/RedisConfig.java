package top.ybq87.redis.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import java.net.UnknownHostException;
import java.time.Duration;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * redis 配置信息
 * @EnableCaching 注解开启缓存
 * @Configuration 注解读取配置文件的信息
 * @Date 创建日期：2019-07-17 15:30
 * @Author 创建人：ly
 */
@EnableCaching
@Configuration
public class RedisConfig extends CachingConfigurerSupport {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisConfig.class);
    
    /**
     * 依据当前的环境选择不同的数据库. 不需要的话可以去掉
     */
    @Value("${spring.profiles.active}")
    private String active;
    
    /**
     * Redis服务器地址
     */
    @Value("${spring.redis.host}")
    private String host;
    /**
     * Redis服务器连接端口
     */
    @Value("${spring.redis.port}")
    private Integer port;
    /**
     * Redis数据库索引（默认为0）
     */
    @Value("${spring.redis.database}")
    private Integer database;
    /**
     * Redis服务器连接密码（默认为空）
     */
    @Value("${spring.redis.password}")
    private String password;
    /**
     * 连接超时时间（毫秒）
     */
    @Value("${spring.redis.timeout}")
    private Duration timeout;
    
    /**
     * 连接池最大连接数（使用负值表示没有限制）
     */
    @Value("${spring.redis.lettuce.pool.max-active}")
    private Integer maxActive;
    /**
     * 连接池最大阻塞等待时间（使用负值表示没有限制）
     */
    @Value("${spring.redis.lettuce.pool.max-wait}")
    private Duration maxWait;
    /**
     * 连接池中的最大空闲连接
     */
    @Value("${spring.redis.lettuce.pool.max-idle}")
    private Integer maxIdle;
    /**
     * 连接池中的最小空闲连接
     */
    @Value("${spring.redis.lettuce.pool.min-idle}")
    private Integer minIdle;
    /**
     * 关闭超时时间
     */
    @Value("${spring.redis.lettuce.shutdown-timeout}")
    private Duration shutdown;
    
    /**
     * 自定义缓存的 key 生成策略, 没有指定key的缓存都使用这个生成.
     * @return
     */
    @Bean
    @Override
    public KeyGenerator keyGenerator() {
        // 设置自动key的生成规则，配置spring boot的注解，进行方法级别的缓存
        // 使用：号进行分割，可以很多显示出层级关系
        // 这里其实就是new了一个KeyGenerator对象，这是 lambda 表达式的写法
        return (target, method, params) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(target.getClass().getName());
            sb.append(".");
            sb.append(method.getName());
            sb.append("[");
            for (Object obj : params) {
                sb.append(String.valueOf(obj));
            }
            sb.append("]");
            return sb.toString();
        };
    }
    
    /**
     * 缓存配置管理器
     */
    @Bean
    @Override
    public CacheManager cacheManager() {
        // 以锁写入的方式创建RedisCacheWriter对象
        RedisCacheWriter writer = RedisCacheWriter.lockingRedisCacheWriter(getConnectionFactory());
        // 创建默认缓存配置对象
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();
        return new RedisCacheManager(writer, config);
    }
    
    /**
     * 往容器中添加RedisTemplate对象，设置序列化方式
     * @param redisConnectionFactory
     * @return
     * @throws UnknownHostException
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
        RedisTemplate<String, Object> template = new RedisTemplate();
        template.setConnectionFactory(redisConnectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(valueSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(valueSerializer());
        template.afterPropertiesSet();
        return template;
    }
    
    /**
     * 使用Jackson序列化器, jackson 在 spring-boot-starter-web 包
     * @return
     */
    private RedisSerializer<Object> valueSerializer() {
        Jackson2JsonRedisSerializer<Object> serializer = new Jackson2JsonRedisSerializer<Object>(Object.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        /*
         * 下面语句的作用是序列化时将对象全类名一起保存下来
         * 设置之后的序列化结果如下：
         *  [
         *   "top.ybq87.mbg.pojo.User",
         *   {
         *     "id": 1,
         *     "name": "Ly"
         *   }
         * ]
         * 不设置的话，序列化结果如下，将无法反序列化
         *  {
         *     "id": 1,
         *     "name": "Ly"
         *   }
         * objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
         */
        // 方法 enableDefaultTyping 在新版 已经被标记成作废，使用下面这个方法代替，仅仅测试了一下，不知道是否完全正确
        objectMapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL);
        serializer.setObjectMapper(objectMapper);
        return serializer;
    }
    
    /**
     * 获取缓存连接
     * @return
     */
    @Bean
    public RedisConnectionFactory getConnectionFactory() {
        // 单机模式
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
        configuration.setHostName(host);
        configuration.setPort(port);
        configuration.setDatabase(database);
        configuration.setPassword(RedisPassword.of(password));
        // 哨兵模式
        // RedisSentinelConfiguration configuration1 = new RedisSentinelConfiguration();
        // 集群模式, 使用官方提供的集群模式即可
        // RedisClusterConfiguration configuration2 = new RedisClusterConfiguration();
        // factory.setShareNativeConnection(false); //是否允许多个线程操作共用同一个缓存连接，默认true，false时每个操作都将开辟新的连接
        return new LettuceConnectionFactory(configuration, getPoolConfig());
    }
    
    /**
     * 获取缓存连接池
     * @return
     */
    @Bean
    public LettucePoolingClientConfiguration getPoolConfig() {
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        config.setMaxTotal(maxActive);
        config.setMaxWaitMillis(maxWait.toMillis());
        config.setMaxIdle(maxIdle);
        config.setMinIdle(minIdle);
        return LettucePoolingClientConfiguration.builder()
                .poolConfig(config)
                .commandTimeout(timeout)
                .shutdownTimeout(shutdown)
                .build();
    }
    
    @Override
    @Bean
    public CacheErrorHandler errorHandler() {
        // 异常处理，当Redis发生异常时，打印日志，但是程序正常走
        LOGGER.info("初始化 -> [{}]", "Redis CacheErrorHandler");
        return new CacheErrorHandler() {
            @Override
            public void handleCacheGetError(RuntimeException e, Cache cache, Object key) {
                LOGGER.error("Redis occur handleCacheGetError：key -> [{}]", key, e);
            }
            
            @Override
            public void handleCachePutError(RuntimeException e, Cache cache, Object key, Object value) {
                LOGGER.error("Redis occur handleCachePutError：key -> [{}]；value -> [{}]", key, value, e);
            }
            
            @Override
            public void handleCacheEvictError(RuntimeException e, Cache cache, Object key) {
                LOGGER.error("Redis occur handleCacheEvictError：key -> [{}]", key, e);
            }
            
            @Override
            public void handleCacheClearError(RuntimeException e, Cache cache) {
                LOGGER.error("Redis occur handleCacheClearError：", e);
            }
        };
    }
}