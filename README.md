### EzOA ![p](http://progressed.io/bar/10)   ![jdk](https://img.shields.io/badge/jdk-1.8-brightgreen.svg)
Easy Office Automation 

* 后端使用IntelliJ IDEA开发，Junit做单元测试，Postman做RESTful API测试
* 前后端分离，提供RESTful API，MVC模式开发
* 选用简洁的SpringBoot核心框架，加快开发，简化配置，主要基于注解配置，类似Spring 5
* SpringBoot 2.0 自带日志接口Slf4j，日志实现logback，可自定义配置文件logback-spring.xml实现指定类日志输出，daily日志等
* @EnableScheduling注解开启定时任务功能，@Scheduled(cron = "0 0 6 * * ?")注解定时方法
* 数据库选用MySQL和Redis
* 应用服务器选用
