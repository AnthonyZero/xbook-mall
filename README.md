## xbook-mall
![https://img.shields.io/badge/springboot-2.1.3-yellow.svg?style=flat-square](https://img.shields.io/badge/springboot-2.1.3-yellow.svg?style=flat-square)
![https://img.shields.io/badge/dubbo-2.6.6-orange.svg?longCache=true&style=flat-square](https://img.shields.io/badge/dubbo-2.6.6-orange.svg?longCache=true&style=flat-square)
![https://img.shields.io/badge/mybatisplus-3.1.0-green.svg?longCache=true&style=flat-square](https://img.shields.io/badge/mybatisplus-3.1.0-green.svg?longCache=true&style=flat-square)

### 如果您觉得有帮助，请点右上角 "Star" 支持一下谢谢

## 项目说明
> 一个书籍电商平台，没有传统电商SPU SKU模型，没有什么高大上的技术也没有复杂的业务逻辑，主要是日常工作总结习惯了Dubbo xml配置 想自己从零开始搭建一个Dubbo微服务，以后需要的时候可以直接去掉业务开箱即用。

## 运行效果

## 技术说明
* 使用Spring Boot 构建整个项目 去除 XML 配置
* 数据库使用MySQL和Redis
* 采用Dubbo作为RPC框架
* 使用dubbo-spring-boot-starter 注解驱动
* 使用mybatis-plus 单独作为dao层
* 数据库连接池使用druid
* 开始使用JDK8特性 lambda Stream Optional
* 前后端完全分离
* 消息中间件采用RocketMQ(待续)
* Docker容器化多应用部署(待续)

## 模块说明

```
xbook-mall -- 父项目，依赖管理
│  ├─xbook-mall-api -- 接口模块
│  │  ├─xbook-mall-cart-api    -- 购物车服务接口
│  │  ├─xbook-mall-order-api   -- 订单服务接口  
│  │  ├─xbook-mall-product-api -- 产品服务接口
│  │  ├─xbook-mall-redis-api   -- Redis服务接口
│  │  ├─xbook-mall-user-api    -- 用户服务接口
│  │  ├─xbook-mall-payment-api -- 支付服务接口（待续）
│  │─xbook-mall-common -- 通用工具,公共依赖
│  ├─xbook-mall-dao    -- mybatis mapper以及代码生成器 
│  ├─xbook-mall-entity -- 数据模型
│  ├─xbook-mall-service-cart    -- 购物车Dubbo服务
│  ├─xbook-mall-service-order   -- 订单Dubbo服务
│  ├─xbook-mall-service-product -- 产品Dubbo服务
│  ├─xbook-mall-service-redis   -- Redis Dubbo服务
│  ├─xbook-mall-service-user    -- 用户Dubbo服务
│  ├─xbook-mall-service-payment -- 支付Dubbo服务（待续）
│  ├─xbook-mall-web -- API接口服务
```

## Dubbo服务本机地址

| 服务名称|Dubbo服务端口  |Rest服务端口| 完成情况|
|:---------------:|:---------------:|:---------------:|:---------------:|
| xbook-mall-service-cart      | 127.0.0.1:20884     |-             |OK |
| xbook-mall-service-order     | 127.0.0.1:20885     |-             |OK |
| xbook-mall-service-product   | 127.0.0.1:20883     |-             |OK |
| xbook-mall-service-redis     | 127.0.0.1:20881    |-              |OK |
| xbook-mall-service-user      | 127.0.0.1:20882    |-              |OK |
| xbook-mall-service-payment   | -                  |-              |TODO|
| xbook-mall-web               | -                  |127.0.0.1:8090 |OK |

## 启动
1. git clone git@github.com:AnthonyZero/xbook-mall.git
2. 创建数据库xbook,执行SQL脚本
3. 本地启动redis zookeeper
4. 根据自己环境修改application-dev.yml中数据源和redis配置，启动Dubbo服务
5. 移步到[xbook-mall-web](https://github.com/AnthonyZero/xbook-mall-web), 启动前端项目
