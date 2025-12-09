# Spring AI Alibaba 演示项目

这是一个演示Spring AI Alibaba各种功能的完整项目，涵盖了文本生成、聊天对话、提示模板、文本处理等多个方面。

## 项目特点

- ✨ **全面功能覆盖**: 包含文本生成、聊天、翻译、总结、分类等多种AI功能
- 📝 **详细中文注释**: 所有代码都有详细的中文注释说明
- 🔧 **模块化设计**: 采用清晰的分层架构，易于理解和扩展
- 📄 **完整文档**: 提供详细的功能说明和使用指南

## 功能概览

| 功能 | 描述 | API端点 |
|------|------|---------|
| 文本生成 | 使用AI模型生成文本内容 | `POST /api/ai/generate` |
| 聊天对话 | 支持连续对话交互 | `POST /api/ai/chat` |
| 提示模板 | 使用模板生成结构化内容 | `POST /api/ai/template` |
| 文本总结 | 对长文本进行摘要总结 | `POST /api/ai/summarize` |
| 文本分类 | 对文本进行分类标注 | `POST /api/ai/classify` |
| 情感分析 | 分析文本情感倾向 | `POST /api/ai/sentiment` |
| 文本翻译 | 多语言文本翻译功能 | `POST /api/ai/translate` |
| 文本改写 | 保持原意的文本改写 | `POST /api/ai/rewrite` |

## 快速开始

### 环境要求

- JDK 17 或更高版本
- Maven 3.6 或更高版本

### 本地运行

```bash
# 克隆项目
git clone <项目地址>
cd spring-ai-alibaba-demo

# 编译并运行
mvn clean compile
mvn spring-boot:run
```

### API测试示例

```bash
# 文本生成
curl -X POST http://localhost:8080/api/ai/generate \
  -H "Content-Type: application/json" \
  -d '{"prompt":"请写一段关于人工智能的介绍"}'

# 模板生成
curl -X POST http://localhost:8080/api/ai/template \
  -H "Content-Type: application/json" \
  -d '{"name":"开发者", "topic":"机器学习"}'
```

## 项目结构

```
spring-ai-alibaba-demo/
├── pom.xml                    # Maven依赖配置
├── README.md                  # 项目说明
├── FEATURES.md                # 功能详细说明
├── src/
│   ├── main/
│   │   ├── java/com/example/springai/
│   │   │   ├── SpringAiAlibabaDemoApplication.java  # 主应用类
│   │   │   ├── controller/AiController.java         # API控制器
│   │   │   ├── service/AiService.java               # AI服务实现
│   │   │   ├── config/AiConfig.java                 # AI配置
│   │   │   └── alibaba/AlibabaAiService.java        # 阿里AI服务模拟
│   │   └── resources/application.properties         # 应用配置
│   └── test/                                          # 测试代码
```

## 配置说明

当前项目使用OpenAI作为演示，当Spring AI Alibaba正式发布后，只需简单替换依赖和配置即可：

1. 在`pom.xml`中替换为Spring AI Alibaba依赖
2. 在`application.properties`中启用Alibaba配置
3. 更新API密钥和其他相关配置

## 扩展功能

项目架构支持以下扩展：

- **函数调用**: 扩展支持AI调用外部函数
- **嵌入向量**: 添加文本嵌入功能
- **流式响应**: 实现流式响应提升体验
- **多模态处理**: 扩展图像、音频等处理能力
- **自定义提示工程**: 构建复杂提示框架

## 学习资源

- 详细功能说明见 [FEATURES.md](./FEATURES.md)
- Spring AI官方文档: https://docs.spring.io/spring-ai/reference/
- 通义千问API文档: https://help.aliyun.com/product/209921.htm

## 注意事项

1. 请妥善保管API密钥，避免泄露
2. 注意AI服务调用的成本控制
3. 生产环境需添加完善的错误处理机制
4. 高并发场景下考虑性能优化策略

---
**提示**: 本项目为演示用途，展示了Spring AI Alibaba的使用方式。在正式版本发布后，可以轻松迁移到真实的通义千问服务。