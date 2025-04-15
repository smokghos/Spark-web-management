# 🚀 Spark Web Management 项目 README

---

## 🌟 项目简介

**Spark Web Management** 是一个基于 **Spring Boot** 的现代化后端管理系统，专注于员工、部门及相关业务的高效管理。通过 **MyBatis** 框架实现数据持久化，并集成了分页查询、日志记录以及阿里云 OSS 文件存储等功能。

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
└── exception/        ⚠️ 全局异常处理
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

## 📊 功能模块

### 1. 员工管理 👥

- **EmpController**: 提供员工增删改查接口。
- **EmpService**: 定义员工业务逻辑。
- **EmpMapper**: 定义员工相关的数据库操作。

### 2. 部门管理 🏢

- **DeptController**: 提供部门增删改查接口。
- **DeptService**: 定义部门业务逻辑。
- **DeptMapper**: 定义部门相关的数据库操作。

### 3. 工作经历管理 📝

- **EmpExprMapper**: 定义员工工作经历的数据库操作。

### 4. 文件上传 📤

- **UploadController**: 提供文件上传接口。
- **AliyunOSSOperator**: 实现文件上传到阿里云 OSS 的功能。

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
   项目默认运行在 `http://localhost:8080`，可通过 Postman 或浏览器访问相关接口。

---

## 🧪 测试用例

测试类位于 `src/test/java/com/spark/`，包含以下内容：

- **Demo.java**: 示例测试类。
- **LogTest.java**: 日志相关测试。

运行测试命令：
```bash
mvn test
```


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


---

## 📞 联系方式

如有任何问题或建议，请提交 issue 。🌟

--- 

✨ **感谢使用 Spark Web Management！** ✨