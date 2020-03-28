## 介绍

blog项目希望构建一个能够快速搭建的个人博客系统，使用一些比较流行的技术,相互交流学习


## 项目文档和演示

#### 文档地址：

搭建中...

#### 项目演示地址：

 [![https://img.shields.io/badge/%E9%A1%B9%E7%9B%AE%E6%BC%94%E7%A4%BA-Blog--Portal--Vue-green](https://img.shields.io/badge/博客门户页面-Blog--Portal--Vue-green)](http://www.ybq87.top) `目前没有接入数据api`

后台管理：搭建中...

## 项目介绍

blog项目是一套可以快速搭建的个人blog系统，包括前台blog系统及后台管理系统，基于SpringBoot+MyBatis实现。 后台管理系统包含权限管理、博文管理、作者管理、评论管理、留言板、友链、等常用的功能模块。前端blog系统包含博文模块、留言板模块、评论模块、搜索模块。
后期不断加入一些比较流行的技术和功能，一边学习一边用于实践,希望大家相互交流.



### 组织结构

*   blog（接口api工程）
    *   common -- 工具类及通用代码
    *   mbg -- MyBatisGenerator生成的数据库操作代码
    *   security -- SpringSecurity封装公用模块
    *   redis — Redis封装
    *   admin -- 后台管理系统API
    *   portal -- 前台商城系统API
*   blog-admin-vue（前端门户）
    *   
*   blog-portal-vue（后台管理页面）
    *   

### 技术选型

#### 后端技术
*   SpringBoot，版本2.2.2
*   SpringSecurity
*   MyBatis
*   MyBatisGenerator
*   PageHelper
*   Swagger-UI
*   Redis
*   Mongo
*   Druid
*   OSS
*   JWT
*   LogStash
*   Lombok
*   ElasticSearch
*   Docker
*   Kafka



#### 前端技术

*   Vue
*   Vue-router
*   Vuex
*   ElementUI
*   Axios
*   Js-cookie
*   nprogress
*   FontAwsome

### 架构设计

待完成...



### 数据库结构

施工中...

## 开发进度

目前还在持续开发中，欢迎大家fork和star

### API部分进度

*   common 模块基础功能
    *   OssUtil 阿里云的 oss管理
    *   统一返回Response
*   security 模块
*   redis 模块封装
    *   目前只是单机redis
*   admin模块
    *   用户管理
    *   管理员管理
    *   权限管理
    *   角色管理
    *   相册管理
    *   博文管理
        *   博文上传，Ueditor+MD编辑器，Ueditor加上秀米插件，目前是Ueditor可以打开秀米编辑后保存到ueditor再编辑，但是没有找到接口重新传输数据到秀米，而且秀米的兼容性也有问题。
        *   MD编辑器已经集成完毕，前端md的展示效果还需要调试
    *   博文分类管理
    *   博文作者管理
    *   评论管理
        *   结构已经设计完毕，代码施工中
    *   留言板
        *   留言板目前采用评论结构，后期考虑加入上传图片的功能。
    *   用户反馈
*   portal模块
    *   博文接口
    *   分类接口
    *   留言板接口
    *   相册接口
    *   评论模块接口
    *   第三方登录模块接口



### Blog-portal-vue进度

*   博文模块
    *   目前只是把列表使用静态数据展示
    *   博文详情还没有
*   关于我们模块
    *   个人简介（想法是做一个个人简历的页面展示）
*   搜索模块
*   相册
*   分类模块
*   热门标签
*   留言板
*   评论模块
*   意见反馈
*   小店;)
*   友链



### Blog-admin-vue进度

*   用户管理
*   后台管理员管理
*   博文管理
*   博文分类管理
*   相册管理
*   评论管理
*   留言板
*   意见反馈
*   各类报表



### 远期计划

计划加入，但是不确定

*   加入小程序功能，实现小程序看博文和评论

*   前端Portal手机版

    *   微信分享功能（Vue的分享貌似有问题，需要攻克）

*   博文生成静态页面加速访问

*   小店功能

    *   购物车

    *   秒杀模块（主要是为了试验秒杀功能）

*   支付功能

*   进化为SpringCloud框架

## 联系我

### 联系方式

![](https://badgen.net/badge/QQ/664162337/blue)![](https://badgen.net/badge/微信/ly19870316/green)![](https://badgen.net/badge/Email/664162337@qq.com/orange)

### 公众号

施工中...

![qrcode_for_gh_c993816af756_258](http://img.ybq87.top/upic/2020/0328/qrcode_for_gh_c993816af756_258.jpg)

## 许可证

Apache License 2.0

Copyright (c) 2020 LY