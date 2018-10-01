### EzOA ![p](http://progressed.io/bar/10)   ![jdk](https://img.shields.io/badge/jdk-1.8-brightgreen.svg)
Easy Office Automation [Demo演示](https://github.com/Stackneveroverflow/ezOA-MiniProgram)

##### 技术选型
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
* 登录
>拿着前端发送过来的code，再加上appID和密钥，向微信接口服务code2session换取session_key和微信号唯一标识openID
>
>用前端发送过来的encryptionData和iv结合session_key按照微信指定的AES-128加密方式解密出用户信息
>
>Redis缓存userinfo, 用户再次登录直接返回

* 考勤
>前端发送来Wi-Fi的ssid和bssid（和定位信息），服务器进行检查，通过则由Redis记录（用Redis的list数据结构
，方便读取第一次打卡和最后一次打卡以及添加打卡记录)，返回今日考勤结果
>
>每日服务器定时读取Redis打卡记录的第一次和最后一次打卡，计算出工时存入MySQL中
>提供接口给前端查询已获工时

* 申请

* 通知

* 个人中心
