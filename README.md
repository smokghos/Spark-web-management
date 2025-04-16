# 🚀 Spark Web Management 项目 README

---

## 🌟 项目简介

**Spark Web Management** 是一个基于 **Spring Boot** ，**MySQL**，**MyBatis** 的管理系统，主要用于管理员工、部门、班级和学生等信息。系统支持分页查询、批量删除、违纪处理、报表统计等功能，并通过 JWT 实现用户认证与授权。


---

## 🔧 技术栈

| 技术/工具        | 描述                          | 版本       |
|------------------|-------------------------------|------------|
| **JDK**          | Java 开发工具包               | 1.8        |
| **框架**         | Spring Boot                   | 2.6.13     |
| **数据库**       | MySQL                         | 8.0.31     |
| **ORM 框架**     | MyBatis                       | 2.2.2      |
| **分页插件**     | PageHelper                    | 1.4.7      |
| **日志**         | Logback                       | 1.2.11     |
| **文件存储**     | 阿里云 OSS SDK                | 3.17.4     |
| **开发工具**     | Maven, Lombok                 | -          |

---

## 🖥️ 环境要求

- **操作系统**: Windows / macOS / Linux
- **JDK版本**: 1.8+
- **构建工具**: Maven
- **数据库**: MySQL（需提前安装并配置）

---

## 📂 项目结构

```plaintext
src/main/java/com/spark/
├── controller/       🌐 控制器层，处理 HTTP 请求
├── mapper/           🗃️ Mapper 接口，定义数据库操作方法
├── pojo/             📦 实体类，对应数据库表结构
├── service/          ⚙️ 服务接口定义
├── service/impl/     💡 服务实现类
├── utils/            🔧 工具类，如阿里云 OSS 操作工具
├── exception/        ⚠️ 全局异常处理
    ├── aop/ # AOP 切面 
    ├── filter/ # 过滤器 
    ├── anno/ # 自定义注解 
    ├── utils/ # 工具类       
```


---

## 🛠️ 核心依赖

| 功能模块         | 依赖名称                          | 版本       |
|------------------|-----------------------------------|------------|
| **Spring Boot**  | spring-boot-starter-web          | 2.6.13     |
| **MyBatis**      | mybatis-spring-boot-starter      | 2.2.2      |
| **数据库连接**   | mysql-connector-j                | 8.0.31     |
| **分页插件**     | pagehelper-spring-boot-starter   | 1.4.7      |
| **阿里云 OSS**   | aliyun-sdk-oss                   | 3.17.4     |
| **日志**         | logback                          | 1.2.11     |

---

## ⚙️ 环境配置

### 数据库配置

在 `src/main/resources/application.yml` 中配置数据库连接信息：

```yaml
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/your_database_name?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC
    username: your_username
    password: your_password
```


### 阿里云 OSS 配置

在 `application.yml` 中配置阿里云 OSS 参数：

```yaml
aliyun:
  oss:
    endpoint: https://oss-cn-beijing.aliyuncs.com
    bucketName: your_bucketName
    region: cn-beijing
```


### 日志配置

日志输出到控制台和文件，默认路径为：`G:\open\Spark-web-management\log\`，建议根据实际需求更改路径，每日生成一个日志文件。

---

## 功能模块

### 1. 员工管理
- **分页查询**: 支持按姓名、职位等条件进行分页查询。
- **批量删除**: 根据 ID 批量删除员工数据。
- **修改员工信息**: 更新员工的基本信息。
- **员工统计**: 提供性别统计和职位统计功能。

### 2. 部门管理
- **查询所有部门**: 获取所有部门信息。
- **添加部门**: 新增部门。
- **删除部门**: 根据 ID 删除部门。

### 3. 班级管理
- **查询所有班级**: 获取所有班级信息。
- **新增班级**: 添加新的班级。
- **修改班级信息**: 更新班级的基本信息。

### 4. 学生管理
- **分页查询**: 支持按班级 ID、学历等条件进行分页查询。
- **批量删除**: 根据 ID 批量删除学生数据。
- **违纪处理**: 对学生的违纪行为进行扣分处理。

### 5. 报表统计
- **员工性别统计**: 统计员工的性别分布。
- **员工职位统计**: 统计每一年入职的员工人数。

### 6. 用户认证
- **登录接口**: 用户登录后获取 JWT 令牌，后续请求需携带该令牌。
- **权限控制**: 使用自定义注解 `@LogOperation` 和 AOP 切面实现操作日志记录。

---

## ▶️ 运行项目

1. **安装依赖**  
   使用 Maven 安装项目依赖：
   ```bash
   mvn clean install
   ```


2. **启动项目**  
   运行主类 `SparkWebManagementApplication` 启动项目：
   ```bash
   java -jar target/Spark-web-management-0.0.1.jar
   ```


3. **访问接口**  
   项目默认运行在 `http://localhost:8080`，API 文档使用 OpenAPI 格式生成，详情见 `otherResources/API/Web开发.openapi.json` 文件。
---

## ⚠️ 注意事项

1. **数据库初始化**  
   在项目根目录下有 SQL 脚本文件，请先执行这些脚本初始化数据库。

2. **文件上传限制**  
   配置文件中设置了单个文件最大 10MB，请求总大小最大 100MB。

3. **日志级别调整**  
   如果需要调试事务管理日志，请在 `application.yml` 中设置：
   ```yaml
   logging:
     level:
       org.springframework.jdbc.support.JdbcTransactionManager: debug
   ```
4. 登录接口返回的 JWT 令牌需在后续请求中作为 Header 参数传递，字段名为 `token`。
5. 所有异常均被捕获并统一处理，返回格式化后的错误信息。
6. 操作日志通过 AOP 切面自动记录。

---

## 📞 联系方式

如有任何问题或建议，请提交 issue 。🌟

--- 

✨ **感谢使用 Spark Web Management！** ✨