### EzOA ![p](http://progressed.io/bar/10)   ![jdk](https://img.shields.io/badge/jdk-1.8-brightgreen.svg)
Easy Office Automation 

####
* 后端使用IntelliJ IDEA开发，Junit做单元测试，Postman做RESTful API测试
* 前后端分离，提供RESTful API，MVC模式开发
* 选用简洁的SpringBoot核心框架，加快开发，简化配置，主要基于注解配置，类似Spring 5
* SpringBoot自带日志接口Slf4j，日志实现logback，可自定义配置文件logback-spring.xml实现指定类日志输出，daily日志等
* @EnableScheduling注解开启定时任务功能，@Scheduled(cron = "0 0 6 * * ?")注解定时方法
* 数据库选用MySQL和Redis
* Maven管理项目，Git版本控制
* 应用服务器选用SpringBoot自带的Embed Tomcat，在application.yml设置部署SSL证书，端口443

##### 部署过程
* mvn clean package -Dmaven.test.skip=true 打包成jar包
* scp .target/ezoa.jar root@[domain]:/javaapps 上传到服务器
* 服务器先执行杀进程脚本再执行启动脚本

kill433.sh
```
#!/bin/sh
PROCESS=`lsof -i:443|grep -v PID|awk '{print $2}'`
for i in $PROCESS
do
 echo "kill the $1 process[$i]"
 kill -9 $i
done
```
start.sh
```
#!/bin/sh
java -Xms128m -Xmx256m -Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=8081,suspend=n -jar ezoa.jar --spring.profiles.active=dev &

#-Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=8081,suspend=n

#设置debug模式，允许tomcat远程连接服务器调试，调试端口可指定，此处为8081

#/data/app/test.jar 指定需要执行的jar包的路径

#--spring.profiles.active=dev 设定SpringBoot运行环境为dev环境

#& 后台模式执行
```
`内存默认比较小，进程运行时容易死掉，改了之后就好了`

---

##### 功能
1. 登录
拿着前端发送过来的code，再
