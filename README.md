# house_info
### 爬取在售和已售房屋的基本信息

### Intelij常用快捷键介绍
* 搜索，control+f，本页检索，shift+control+f,全文检索，连续两下shift,检索文件；替换操作将f换成r
* 格式化，macOS为command+option+L,


### 项目文件介绍
* pom.xml 管理项目依赖的第三方jar包，根据jar包名和版本号，可以直接使用，需安装maven
* target 项目编译后自动生成的文件，如class文件和jar包
* test 框架自动生成，一般不提交到到版本控制
* resources 资源文件夹，application.properties是插件自动生成，为spring-boot项目的配置文件，包括对外的端口、使用的数据库参数等，其它属性可以在spring-boot官网查阅
* java文件夹，.java文件存放的地方，其中HouseInfoApplication是main函数，项目的启动入口，框架会做一些事情

