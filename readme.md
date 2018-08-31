### 远程部署

-   mvn clean tomcat7:deploy -Ptest
-   mvn clean tomcat7:redeploy -Ptest
-   mvn clean tomcat8:deploy -Ptest
-   mvn clean tomcat8:redeploy -Ptest

### 骨架 命令

+ mvn archetype:create-from-project
+ cd target/generated-sources/archetype/
+ mvn install
+ mvn archetype:crawl

### quickstart maven 骨架

### quickwebapp maven web骨架

### core 提供常用的工具类

### hibernate 提供dao层的封装

### task 提供常用定时任务

### ssh 是hibernate+spring+spring mvc

