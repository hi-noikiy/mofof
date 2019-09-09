#MoFoFServer

#代码目录
- controller MVC的控制器,接收网络请求,每个业务逻辑组使用一个,调用service
- dto 辅助的数据对象
- entity 数据库实体对象,按业务逻辑划分,自动生成表
- repository 数据库操作类(相当于DAO),每个生成的表对应一个
- service 业务逻辑封装,调用repository,管理事务,每个业务逻辑组使用一个
- utils 工具类
- MoFoFServerApplication 入口类
- resource 资源,包括配置文件/初始化数据

# 命令说明

- 可用开发工作下载gradle依赖
- ./gradlew build 可以打包
- ./gradlew bootRun来启动
- 调试运行直接运行MofofserverApplication

# DEMO

- http://47.75.180.158:8081
