### rainbow 微服务权限系统
![https://img.shields.io/badge/license-Apache%202.0-blue.svg?longCache=true&style=flat-square](https://img.shields.io/badge/license-Apache%202.0-blue.svg?longCache=true&style=flat-square)
![https://img.shields.io/badge/springcloud-Hoxton.RELEASE-yellow.svg?style=flat-square](https://img.shields.io/badge/springcloud-Hoxton.RELEASE-yellow.svg?style=flat-square)
![https://img.shields.io/badge/SpringCloudAlibaba-2.1.1.RELEASE-blueviolet.svg?style=flat-square](https://img.shields.io/badge/SpringCloudAlibaba-2.1.1.RELEASE-blueviolet.svg?style=flat-square)
![https://img.shields.io/badge/springboot-2.2.0.RELEASE-brightgreen.svg?style=flat-square](https://img.shields.io/badge/springboot-2.2.0.RELEASE-brightgreen.svg?style=flat-square)


rainbow是一款使用Spring Cloud Hoxton.RELEASE、Spring Cloud OAuth2 & Spring Cloud Alibaba构建的权限管理系统：

# 搭建流程
1.搭建auth服务

    1. 认证服务器

    2. 第三方客户端信息 存入mysql 和 redis  可以动态接入

    3. 资源服务器配置 （抽取到security-starter）

    4. 认证服务器配置

    5. 开启验证码  -- 利用redis  将验证码值存入redis


2.搭建redis-starter

     1.编写 redis配置类 序列化

     2.编写redis工具类

     3.利用自动装配注入spring ioc

3.搭建common-core

     1.维护系统常量类

     2.维护常用工具类

     3.全局异常处理基类
   
4.搭建datasource-starter

     1.配置分页插件

     2.sql数据拦截

5.搭建security-staarter

     1.编写自动注入配置类  把权限相关的配置 自动注入

     2.处理401 403异常

     3.微服务防护拦截器  不允许请求绕过网关

     4.配置feign  feign请求不会携带token 手动拦截请求  请求头放入token


6.搭建gateway网关

    1.配置路由规则

    2.配置防护禁止请求绕过网关

    3.限流和黑名单配置

    4.跨域配置

    5.服务转发日志

7.整合swagger接口文档   

8.搭建rainbow-system 系统服务

    1.RBAC及业务处理

    2.整合easypoi导入导出
    
    3.利用redis实现数据字典功能

9.搭建rainbow-bus 消息服务

    1.利用动态代理处理重复消费和消息丢失的问题

    2.消息日志记录消息,利用定时任务对失败的消息重新投递
    
    3.实现登录成功发送邮件功能


10.搭建rainbow-search全文检索服务(待完成)
  
    1.搭建elasticsearch服务和kibana可视化界面。
    
    2.创建es的索引和mapping  
    （1) es中有index,type,document,mapping的概念。index类比于mysql的数据库,type类比于表，document类比于一条记录，mapping类比于字段的约束条件，varchar之类的
    （2) es中的倒排索引。lasticSearch引擎把文档数据写入到倒排索引（Inverted Index）的数据结构中，倒排索引建立的是分词（Term）和文档（Document）之间的映射关系，在倒排索引中，数据是面向词（Term）而不是面向文档的。
    （3) 下载对应版本的分词器，我这里使用ik分词器
       
    3.搭建cannal同步mysql的数据到es中。
     （1）当用户进行业务操作（增删改）时,canal监听mysql的binlog日志，伪装成从节点得到这些信息，然后把mysql数据同步到es，做到一致性。
       
     
  

11.搭建rainbow-upload文件上传服务(已完成 利用fastDfs)

12.搭建rainbow-apm监控服务(待完成)

13.搭建rainbow-generator 逆向工程生成代码(已完成)

14.搭建rainbow-job 定时任务(待完成)

15.分布式事务处理(待完成)

16.链路追踪(待完成)

### 服务模块

rainbow模块：

服务名称 | 端口 | 描述
---|---|---
rainbow-auth| 8881| 微服务认证授权
rainbow-gateway| 8882 |微服务网关
rainbow-server-system| 8883 | 系统资源微服务
nacos| 8848 | 注册中心
rainbow-bus| 8885 |消息微服务
rainbow-search| 8886 |全文检索微服务
rainbow-apm| 8887 |监控微服务
rainbow-upload| 8888 |文件上传微服务
rainbow-generator| 8889 |逆向工程-代码生成器

### 目录结构
```
├─rainbow-auth                                                  ------ 微服务认证服务器
├─rainbow-common                                                ------ 通用模块
│      ├─rainbow-common-datasource-starter                      ------ 数据权限处理和分页插件starter
│      ├─rainbow-common-redis-starter                           ------ redis缓存starter
│      ├─rainbow-common-security-starter                        ------ oauth2.0的一些配置
│      ├─rainbow-common-core                                    ------ 通用工具及系统通用处理
├─rainbow-gateway                                               -----  微服务网关
└─rainbow-server                                                ------ 资源服务器
│   ├─rainbow-system-api                                        ------ 资源服务器api
│   └─rainbow-system-server                                     ------ 资源服务器业务
├─rainbow-bus                                                   ------ 消息微服务  
├─rainbow-upload                                                ------ 文件上传服务  
├─rainbow-apm                                                   ------ 监控服务  
├─rainbow-search                                                ------ 全文检索服务  
```



