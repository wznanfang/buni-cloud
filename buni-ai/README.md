# Buni AI 模块

基于 Spring AI 生态的智能对话服务模块，支持多种AI模型和高级功能。

## 功能特性

### 基础对话功能
- ✅ 支持千帆AI模型（Spring AI集成）
- ✅ 支持讯飞星火（原生SDK + 适配器）
- ✅ 统一接口，易于切换模型
- ✅ 兼容原有接口

### 高级功能
- ✅ 流式对话（Server-Sent Events）
- ✅ 多轮对话（保持上下文）
- ✅ 文档问答（简化版）
- ✅ 代码生成
- ✅ 图片描述生成
- ✅ 带系统提示的对话

## 技术栈

- **Spring AI 0.8.0** - AI生态框架
- **Spring Boot 3.x** - 应用框架
- **Spring WebFlux** - 响应式编程（流式对话）
- **Knife4j** - API文档
- **讯飞星火SDK** - 原生SDK集成

## 支持的AI模型

### 1. 百度千帆（Spring AI）
- 模型：ERNIE-Bot-4, ERNIE-Bot-turbo
- 配置：`spring.ai.baidu.qianfan.*`

### 2. 讯飞星火（原生SDK + 适配器）
- 模型：spark-v3, spark-v2
- 配置：`buni.ai.spark.*`

## 快速开始

### 1. 启动服务

```bash
mvn spring-boot:run -pl buni-ai-service
```

### 2. 访问API文档

```
http://localhost:10004/doc.html
```

## API接口

### 基础对话接口

#### 默认AI对话
```http
POST /v1/ai/chat
Content-Type: application/json

{
  "question": "你好，请介绍一下Spring AI",
  "uid": "user123"
}
```

#### 指定模型对话
```http
POST /v1/ai/chat/qianfan
POST /v1/ai/chat/spark
```

### 高级功能接口

#### 流式对话
```http
POST /v1/ai/advanced/stream-chat
Content-Type: application/json
Accept: text/event-stream

{
  "question": "请详细介绍一下Java编程语言",
  "uid": "user123"
}
```

#### 多轮对话
```http
POST /v1/ai/advanced/multi-turn-chat?sessionId=session123
Content-Type: application/json

{
  "question": "继续刚才的话题",
  "uid": "user123"
}
```

#### 代码生成
```http
POST /v1/ai/advanced/code-generation
Content-Type: application/x-www-form-urlencoded

requirement: 实现一个快速排序算法
language: Java
```

## 配置说明

### application-dev.yml

```yaml
spring:
  ai:
    # 千帆配置
    baidu:
      qianfan:
        api-key: your-qianfan-api-key
        secret-key: your-qianfan-secret-key
        chat:
          options:
            model: ERNIE-Bot-4
            temperature: 0.7
            max-tokens: 1000

# 自定义配置
buni:
  ai:
    # 千帆配置（兼容旧配置）
    qianfan:
      apiKey: your-qianfan-api-key
      secretKey: your-qianfan-secret-key
    # 讯飞星火配置
    spark:
      appId: your-spark-app-id
      apiKey: your-spark-api-key
      apiSecret: your-spark-api-secret
```

## 架构说明

### 1. Spring AI集成
- 千帆通过Spring AI官方starter集成
- 自动配置ChatModel和ChatClient

### 2. 讯飞星火适配器
- 使用原生SDK `xunfei-spark4j`
- 通过 `SparkChatModelAdapter` 适配Spring AI接口
- 实现Message类型转换

### 3. 配置管理
- Spring AI配置：`spring.ai.*`
- 自定义配置：`buni.ai.*`
- 保持向后兼容

## 开发指南

### 添加新的AI模型

1. 在 `pom.xml` 中添加对应的依赖
2. 创建配置类（如需要）
3. 创建适配器（如使用原生SDK）
4. 在 `SpringAiConfig` 中添加Bean配置
5. 在 `AiChatServiceImpl` 中添加对应的聊天方法
6. 在 `AiController` 中添加对应的接口

### 错误处理

所有AI服务都使用统一的异常处理：

```java
try {
    // AI调用
} catch (Exception e) {
    throw new CustomException(ErrorEnum.AI_TALK_ERROR.getCode(), 
                            ErrorEnum.AI_TALK_ERROR.getMessage());
}
```

## 常见问题

### Q: 如何切换默认AI模型？
A: 修改 `SpringAiConfig` 中的 `@Primary` 注解，或者直接调用指定模型的接口。

### Q: 讯飞星火为什么使用适配器？
A: 讯飞星火目前没有官方的Spring AI starter，所以使用原生SDK + 适配器的方式。

### Q: 流式对话不工作？
A: 确保客户端支持 Server-Sent Events，并且设置了正确的 Content-Type。

### Q: 如何清理会话上下文？
A: 会话上下文会自动限制长度，也可以添加清理接口手动清理。

## 更新日志

### v2.1.0 (2024-12-19)
- ✅ 集成Spring AI生态
- ✅ 支持千帆AI模型
- ✅ 讯飞星火适配器
- ✅ 新增流式对话功能
- ✅ 新增多轮对话功能
- ✅ 新增代码生成功能
- ✅ 优化错误处理和日志
- ✅ 简化文档处理功能

### v1.0.0 (2024-08-01)
- ✅ 基础千帆和讯飞星火集成
- ✅ 简单对话功能 