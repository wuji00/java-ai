# Spring AI Alibaba 功能演示项目

## 项目概述

本项目演示了Spring AI Alibaba的各种功能，包括文本生成、聊天对话、提示模板、文本处理等。虽然当前Spring AI Alibaba可能还没有正式的Maven依赖，但本项目提供了完整的使用示例和架构，以便在正式版本发布后快速集成。

## 功能列表

### 1. 基本文本生成
- **功能描述**: 使用通义千问模型生成文本内容
- **实现类**: `AiService.generateText()`
- **API接口**: `POST /api/ai/generate`
- **使用场景**: 文本创作、内容生成、创意写作等

### 2. 聊天对话功能
- **功能描述**: 支持连续的对话交互，保持上下文
- **实现类**: `AiService.chat()`
- **API接口**: `POST /api/ai/chat`
- **使用场景**: 智能客服、对话机器人、问答系统等

### 3. 提示模板
- **功能描述**: 使用模板生成结构化内容
- **实现类**: `AiService.generateWithTemplate()`
- **API接口**: `POST /api/ai/template`
- **使用场景**: 个性化内容生成、模板化输出等

### 4. 文本总结
- **功能描述**: 对长文本进行摘要总结
- **实现类**: `AiService.summarizeText()`
- **API接口**: `POST /api/ai/summarize`
- **使用场景**: 文档摘要、新闻摘要、内容提炼等

### 5. 文本分类
- **功能描述**: 对文本进行分类标注
- **实现类**: `AiService.classifyText()`
- **API接口**: `POST /api/ai/classify`
- **使用场景**: 内容分类、情感分类、主题识别等

### 6. 情感分析
- **功能描述**: 分析文本的情感倾向
- **实现类**: `AiService.analyzeSentiment()`
- **API接口**: `POST /api/ai/sentiment`
- **使用场景**: 情感分析、舆情监控、用户反馈分析等

### 7. 文本翻译
- **功能描述**: 将文本从一种语言翻译到另一种语言
- **实现类**: `AiService.translateText()`
- **API接口**: `POST /api/ai/translate`
- **使用场景**: 多语言内容生成、文档翻译等

### 8. 文本改写
- **功能描述**: 保持原意的前提下改写文本
- **实现类**: `AiService.rewriteText()`
- **API接口**: `POST /api/ai/rewrite`
- **使用场景**: 内容优化、文本润色、去重等

## 配置说明

### 应用配置 (application.properties)

```properties
# Spring AI Alibaba 配置（注释掉，因为目前没有正式的Maven依赖）
# spring.ai.alibaba.access-key-id=${ALIBABA_ACCESS_KEY_ID:your-access-key-id}
# spring.ai.alibaba.access-key-secret=${ALIBABA_ACCESS_KEY_SECRET:your-access-key-secret}
# spring.ai.alibaba.region-id=${ALIBABA_REGION_ID:cn-hangzhou}
# spring.ai.alibaba.dash-scope.base-url=${ALIBABA_BASE_URL:https://dashscope.aliyuncs.com/api/v1}

# 通义千问模型配置（注释）
# spring.ai.alibaba.chat.options.model=qwen-turbo
# spring.ai.alibaba.chat.options.temperature=0.7
# spring.ai.alibaba.chat.options.top-p=0.8
# spring.ai.alibaba.chat.options.max-tokens=2048

# 嵌入模型配置（注释）
# spring.ai.alibaba.embedding.options.model=text-embedding-v1

# 为了演示目的，使用OpenAI配置
spring.ai.openai.api-key=${OPENAI_API_KEY:your-openai-api-key}
spring.ai.openai.base-url=${OPENAI_BASE_URL:https://api.openai.com/v1}
spring.ai.openai.chat.options.model=gpt-3.5-turbo

# 服务器配置
server.port=8080

# 日志配置
logging.level.org.springframework.ai=DEBUG
logging.level.com.example.springai=DEBUG
```

### 配置说明

1. **Alibaba配置**: 已注释，因为当前Spring AI Alibaba可能没有公开的Maven依赖
2. **OpenAI配置**: 用于演示目的，实际部署时替换为Alibaba配置
3. **环境变量**: 使用环境变量管理敏感信息，如API密钥

## API接口说明

### 1. 文本生成接口
- **URL**: `POST /api/ai/generate`
- **请求体**:
```json
{
  "prompt": "请输入您的提示文本"
}
```
- **响应**: 生成的文本内容

### 2. 聊天接口
- **URL**: `POST /api/ai/chat`
- **请求体**:
```json
{
  "messages": [
    {
      "type": "user",
      "content": "消息内容"
    }
  ],
  "currentMessage": "当前用户消息"
}
```
- **响应**: AI回复内容

### 3. 模板生成接口
- **URL**: `POST /api/ai/template`
- **请求体**:
```json
{
  "name": "用户名",
  "topic": "主题"
}
```
- **响应**: 生成的个性化内容

### 4. 文本总结接口
- **URL**: `POST /api/ai/summarize`
- **请求体**:
```json
{
  "prompt": "需要总结的文本"
}
```
- **响应**: 总结后的内容

### 5. 文本分类接口
- **URL**: `POST /api/ai/classify`
- **请求体**:
```json
{
  "text": "待分类的文本",
  "categories": ["类别1", "类别2", "类别3"]
}
```
- **响应**: 分类结果

### 6. 情感分析接口
- **URL**: `POST /api/ai/sentiment`
- **请求体**:
```json
{
  "prompt": "待分析的文本"
}
```
- **响应**: 情感分析结果

### 7. 文本翻译接口
- **URL**: `POST /api/ai/translate`
- **请求体**:
```json
{
  "text": "待翻译的文本",
  "targetLanguage": "目标语言"
}
```
- **响应**: 翻译后的文本

### 8. 文本改写接口
- **URL**: `POST /api/ai/rewrite`
- **请求体**:
```json
{
  "prompt": "待改写的文本"
}
```
- **响应**: 改写后的文本

## 项目结构

```
spring-ai-alibaba-demo/
├── pom.xml                    # Maven配置文件
├── FEATURES.md               # 功能说明文档
├── README.md                 # 项目说明文档
├── src/
│   ├── main/
│   │   ├── java/com/example/springai/
│   │   │   ├── SpringAiAlibabaDemoApplication.java  # 主应用类
│   │   │   ├── controller/
│   │   │   │   └── AiController.java               # API控制器
│   │   │   ├── service/
│   │   │   │   └── AiService.java                  # AI服务类
│   │   │   ├── config/
│   │   │   │   └── AiConfig.java                   # AI配置类
│   │   │   └── alibaba/
│   │   │       └── AlibabaAiService.java           # 阿里AI服务模拟
│   │   └── resources/
│   │       └── application.properties             # 应用配置文件
│   └── test/
│       └── java/com/example/springai/
│           └── SpringAiAlibabaDemoApplicationTests.java  # 测试类
```

## 使用说明

### 1. 环境准备

1. 安装JDK 17+
2. 安装Maven 3.6+
3. 准备OpenAI API密钥（用于演示）

### 2. 项目启动

```bash
# 克隆项目
git clone <项目地址>
cd spring-ai-alibaba-demo

# 编译项目
mvn clean compile

# 运行项目
mvn spring-boot:run
```

### 3. API测试

启动服务后，可以使用curl或Postman等工具测试API：

```bash
# 文本生成测试
curl -X POST http://localhost:8080/api/ai/generate \
  -H "Content-Type: application/json" \
  -d '{"prompt":"写一首关于春天的诗"}'

# 模板生成测试
curl -X POST http://localhost:8080/api/ai/template \
  -H "Content-Type: application/json" \
  -d '{"name":"张三", "topic":"人工智能"}'
```

## 实际部署配置

当Spring AI Alibaba正式发布后，您需要进行以下配置更改：

### 1. 更新pom.xml依赖

```xml
<dependencies>
    <!-- 替换为Spring AI Alibaba依赖 -->
    <dependency>
        <groupId>org.springframework.ai</groupId>
        <artifactId>spring-ai-alibaba-spring-boot-starter</artifactId>
        <version>最新版本</version>
    </dependency>
</dependencies>
```

### 2. 更新配置文件

```properties
# 启用Alibaba配置
spring.ai.alibaba.access-key-id=your-access-key-id
spring.ai.alibaba.access-key-secret=your-access-key-secret
spring.ai.alibaba.region-id=cn-hangzhou
spring.ai.alibaba.dash-scope.base-url=https://dashscope.aliyuncs.com/api/v1

# 通义千问模型配置
spring.ai.alibaba.chat.options.model=qwen-turbo
spring.ai.alibaba.chat.options.temperature=0.7
spring.ai.alibaba.chat.options.top-p=0.8
spring.ai.alibaba.chat.options.max-tokens=2048

# 嵌入模型配置
spring.ai.alibaba.embedding.options.model=text-embedding-v1

# 禁用OpenAI配置
# spring.ai.openai.api-key=...
```

## 注意事项

1. **API密钥安全**: 请勿将API密钥硬编码在代码中，使用环境变量或配置中心管理
2. **费用控制**: AI服务调用会产生费用，请注意控制调用频率和数量
3. **错误处理**: 在生产环境中应添加适当的错误处理和重试机制
4. **性能优化**: 对于高并发场景，考虑使用缓存和异步处理

## 扩展功能

本项目架构支持以下扩展：

1. **函数调用**: 可以扩展支持AI模型调用外部函数
2. **嵌入向量**: 可以添加文本嵌入功能，用于相似度计算
3. **流式响应**: 可以实现流式响应，提升用户体验
4. **多模态**: 可以扩展支持图像、音频等多模态处理
5. **自定义提示工程**: 可以构建更复杂的提示工程框架

## 参考文档

- [Spring AI官方文档](https://docs.spring.io/spring-ai/reference/)
- [通义千问API文档](https://help.aliyun.com/product/209921.htm)
- [DashScope API文档](https://dashscope.aliyuncs.com/)