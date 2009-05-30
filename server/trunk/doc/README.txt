Mini-Pie Deployment Guide:
update 5/30/2009

要配置认证，需要将minipie-jaas.config文件复制到Tomcat安装目录的conf文件夹中，并在Tomcat启动时的JVM参数中加上：

	-Djava.security.auth.login.config="$CATALINA_HOME/conf/minipie-jaas.config"

其中$CATALINA_HOME为Tomcat的主目录。

比如，如果Tomcat安装在：

	C:/Program Files/Apache Software Foundation/Tomcat 6.0

那么就在JVM参数中加上：

	-Djava.security.auth.login.config="C:/Program Files/Apache Software Foundation/Tomcat 6.0/conf/minipie-jaas.config"

配置结束后可以浏览器通过访问http://localhost:8080/Mini-Pie/services/auth来检验配置是否成功。

目前数据库中几个可用的用户名和密码：

      id: 1
username: KittieDSherrard
password: 52240424

      id: 2
username: PhillipR
password: 328werD

      id: 3
username: parkh
password: qwerrwszds

      id: 4
username: tswang
password: rtj67jkll

      id: 5
username: naotatsu
password: 566753

      id: 6
username: ajdin
password: 1234456`

如果数据库中没有上述用户，还需要执行数据库脚本db_script_new.sql。