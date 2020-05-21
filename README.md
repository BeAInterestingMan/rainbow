### rainbo 微服务权限系统
![https://img.shields.io/badge/license-Apache%202.0-blue.svg?longCache=true&style=flat-square](https://img.shields.io/badge/license-Apache%202.0-blue.svg?longCache=true&style=flat-square)
![https://img.shields.io/badge/springcloud-Hoxton.RELEASE-yellow.svg?style=flat-square](https://img.shields.io/badge/springcloud-Hoxton.RELEASE-yellow.svg?style=flat-square)
![https://img.shields.io/badge/SpringCloudAlibaba-2.1.1.RELEASE-blueviolet.svg?style=flat-square](https://img.shields.io/badge/SpringCloudAlibaba-2.1.1.RELEASE-blueviolet.svg?style=flat-square)
![https://img.shields.io/badge/springboot-2.2.0.RELEASE-brightgreen.svg?style=flat-square](https://img.shields.io/badge/springboot-2.2.0.RELEASE-brightgreen.svg?style=flat-square)
![https://img.shields.io/badge/vue-2.6.10-orange.svg?style=flat-square](https://img.shields.io/badge/vue-2.6.10-orange.svg?style=flat-square)

rainbow是一款使用Spring Cloud Hoxton.RELEASE、Spring Cloud OAuth2 & Spring Cloud Alibaba构建的权限管理系统：

1. 前后端分离架构，客户端和服务端纯Token交互；
 
2. 认证服务器与资源服务器分离，方便接入自己的微服务系统；

3. 微服务防护，客户端请求资源只能通过微服务网关获取；

4. 集成Prometheus，SpringBootAdmin，多维度监控微服务；

5. 集成Spring Cloud Alibaba Nacos服务治理和集中配置管理；

6. 网关限流，网关黑名单限制，网关日志（WebFlux编程实践）；

7. ~~集成Zipkin，方便跟踪Feign调用链~~，集成Skywalking APM；

8. 集成ELK，集中管理日志，便于问题分析；

9. 微服务Docker化，使用Docker Compose一键部署；

10. 支持Kubernetes集群部署；

11. 提供详细的使用文档和搭建教程；

12. 前后端请求参数校验，Excel导入导出，代码生成等。



### 服务模块

rainbow模块：

服务名称 | 端口 | 描述
---|---|---
rainbow-Auth| 8881| 微服务认证服务器 
rainbow-server-System| 8882 | 微服务子系统（资源服务器）
nacos| 8848 | 注册中心
rainbow-gateway|8882|微服务网关
rainbow-bus|8883|微服务消息系统

### 目录结构
```
├─rainbow-auth                    ------ 微服务认证服务器
├─rainbow-common                                                ------ 通用模块
│      ├─rainbow-common-datasource-starter                      ------ 数据权限处理和分页插件starter
│      ├─rainbow-common-redis-starter                           ------ redis缓存starter
│      ├─rainbow-common-security-starter                        ------ oauth2.0的一些配置
│      ├─rainbow-common-core                                    ------ 通用工具及系统通用处理
│      └─rainbow-common-es                                      ------ es全文检索服务
├─rainbow-gateway                                               -----  微服务网关
└─rainbow-server                                                ------ 资源服务器
│   ├─rainbow-system-api                                        ------ 资源服务器api
│   └─rainbow-server-server                                     ------ 资源服务器业务处理
├─rainbow-bus                                                   ------ 消息微服务  
```



