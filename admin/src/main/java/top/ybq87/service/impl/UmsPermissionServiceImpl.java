package top.ybq87.service.impl;

import com.github.pagehelper.PageHelper;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.ybq87.common.response.CommonResult;
import top.ybq87.dao.UmsPermissionDao;
import top.ybq87.dto.UmsPermissionNode;
import top.ybq87.mbg.mapper.UmsPermissionMapper;
import top.ybq87.mbg.model.UmsPermission;
import top.ybq87.mbg.model.UmsPermissionExample;
import top.ybq87.service.UmsPermissionService;

/**
 * 后台用户权限接口实现类
 *
 * @author 创建人：ly 664162337@qq.com
 * @date 创建日期：2020/2/4 19:43
 */
@Service
public class UmsPermissionServiceImpl implements UmsPermissionService {
    
    @Autowired
    private UmsPermissionMapper permissionMapper;
    
    @Autowired
    private UmsPermissionDao permissionDao;
    
    @Override
    public List<UmsPermission> list(String keyword, Integer status, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return permissionDao.getList(keyword, status);
    }
    
    @Override
    public int create(UmsPermission permission) {
        permission.setIcon("");
        permission.setStatus(1);
        permission.setCreateTime(new Date());
        permission.setSort(0);
        return permissionMapper.insert(permission);
    }
    
    @Override
    public void init() {
        //INSERT INTO `ums_permission` VALUES ('1', '0', '商品', null, null, '0', null, '1', '2018-09-29 16:15:14', '0');
        //INSERT INTO `ums_permission` VALUES ('2', '1', '商品列表', 'pms:product:read', null, '1', '/pms/product/index', '1', '2018-09-29 16:17:01', '0');
    }
    
    @Override
    public CommonResult<List<UmsPermissionNode>> treeList() {
        List<UmsPermission> permissionList = permissionMapper.selectByExample(new UmsPermissionExample());
        List<UmsPermissionNode> result = permissionList.stream()
                .filter(permission -> permission.getPid().equals(0L))
                .map(permission -> covert(permission, permissionList)).collect(Collectors.toList());
        return CommonResult.success(result);
    }
    
    @Override
    public int update(Long id, UmsPermission permission) {
        permission.setId(id);
        permission.setCreateTime(null);
        return permissionMapper.updateByPrimaryKeySelective(permission);
    }
    
    @Override
    public int delete(List<Long> ids) {
        UmsPermissionExample example = new UmsPermissionExample();
        example.createCriteria().andIdIn(ids);
        return permissionMapper.deleteByExample(example);
    }
    
    /**
     * 将权限转换为带有子级的权限对象 当找不到子级权限的时候map操作不会再递归调用covert
     */
    private UmsPermissionNode covert(UmsPermission permission, List<UmsPermission> permissionList) {
        UmsPermissionNode node = new UmsPermissionNode();
        BeanUtils.copyProperties(permission, node);
        List<UmsPermissionNode> children = permissionList.stream()
                .filter(subPermission -> subPermission.getPid().equals(permission.getId()))
                .map(subPermission -> covert(subPermission, permissionList)).collect(Collectors.toList());
        node.setChildren(children);
        return node;
    }
}
