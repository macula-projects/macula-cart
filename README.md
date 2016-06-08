#Macula Cart(网上商城示例)

###运行本示例的准备：

* 安装mysql和redis
* 需要提供一个mysql数据库，创建好macula3和${rootArtifactId}，注意修改mysql数据库的相关连接信息
* 注意修改redis连接的相关信息
* 以上配置保存在applicationContext-root.xml中，可以根据需要修改
* CustomMyUserLoginRepopsitory类需要关注，根据你的用户表获取用户信息构造凭据
* 如果你要改变登录、菜单等信息，可以关注CustomMyAppController类
* 删除{rootArtifactId}-parent/pom.xml文件中parent的部分
* 启动运行
* 本程序中的演示程序URL是http://localhost:8080/${rootArtifactId}-webapp/admin/demo/application/list
* 为了可以访问上述地址，你需要配置菜单、功能并做相应的授权

默认登录地址是 http://localhost:8080/${rootArtifactId}-webapp/login?form=true
默认登录用户是 admin,密码是infi123*
后端应用地址：http://localhost:8080/${rootArtifactId}-webapp/admin
前端应用地址：http://localhost:8080/${rootArtifactId}-webapp/front

1.3 release
