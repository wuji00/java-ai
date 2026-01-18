# Lynxe (JManus) Clone Specification

> **目标**: 在 `myManus` 目录下“一比一复刻” JManus 项目，旨在通过重建过程深度理解其架构和逻辑。
> **原则**: 理解优先。不仅是复制代码，更是重建设计思路。

## 1. 项目概览 (Overview)

**Lynxe** 是一个基于 Spring AI Alibaba 的纯 Java 实现的 Manus (多 Agent 协作系统)。其核心在于利用 LLM 进行任务规划 (Planning)、工具调用 (Tool Use) 和 执行记录 (Recording)，实现如数据挖掘、自动化报表等复杂任务。

*   **核心包名**: `com.mymanus`
*   **后端**: Spring Boot 3.5.6 + Java 17
*   **前端**: Vue 3 + TypeScript + Ant Design Vue (复刻策略: 移植并适配)
*   **数据库**: H2 (In-Memory)
*   **AI Provider**: Alibaba DashScope (通义千问)

## 2. 系统架构 (Architecture)

### 2.1 后端分层
*   **Interface Layer (`controller`)**: 处理 HTTP 请求 (REST API, SSE 流式响应).
*   **Agent Layer (`agent`)**: 封装 Agent 行为模式 (ReAct, Dynamic)。
*   **Runtime Layer (`runtime`)**: 任务执行引擎，管理 `Plan`, `Step`, `Task` 的生命周期。
*   **Tool Layer (`tool`)**: 工具集实现 (Browser, File, Search, Code Execution)。
*   **Service Layer (`service`)**: 业务逻辑 (User, Config, Memory)。
*   **Infrastructure Layer (`repository`, `config`)**: 数据持久化和框架配置。

### 2.2 核心数据流
1.  **用户输入**: 用户通过 Web UI 发送任务 -> `RuntimeController`。
2.  **任务初始化**: `RootTaskManager` 创建根任务。
3.  **规划 (Planning)**: LLM (via `LlmService`) 生成初步 Plan。
4.  **循环执行 (Loop)**:
    *   `PlanExecutor` 调度下一步。
    *   `Agent` (ReAct) 思考 (`Think`) 并决定行动 (`Act`)。
    *   `ToolService` 执行工具调用 (e.g., Search, Read File)。
    *   结果回传给 LLM。
5.  **记录 (Recording)**: 所有步骤通过 `PlanExecutionRecorder` 记录并推送到前端。

## 3. 详细复刻路线图 (Detailed Roadmap)

为了深度理解，我们将复刻过程细分为 9 个阶段，每个阶段包含核心逻辑实现和验证点。

### Phase 1: Skeleton & Configuration (骨架与配置)
> **目标**: 建立地基，确保 Spring Boot 容器和数据库连接正常。
*   [x] 初始化 Maven 项目结构 (`com.mymanus`).
*   [x] 配置 `pom.xml`: 引入 Spring Boot Web, Data JPA, H2, Hutool, Lombok.
*   [x] 建立基础架构: `Application.java`, `application.yml`.
*   [x] **验证**: 项目成功启动，H2 Console 可访问。

### Phase 2: LLM Foundation (LLM 接入)
> **目标**: 打通与“大脑”的连接。
*   [x] 引入 Spring AI Alibaba 依赖 (使用了 1.0.0-M2).
*   [x] 封装 `LlmService`: 统一 LLM 调用接口。
*   [x] 配置 DashScope API Key (通过环境变量 `DASHSCOPE_API_KEY`).
*   [x] **验证**: 编写单元测试，成功从 LLM 获取“Hello World”响应 (通过 `/chat` 端点验证).

### Phase 3: The Agent Core (Agent 核心抽象)
> **目标**: 构建能“思考”的最小单元。
*   [x] 定义 `Agent` 接口与 `BaseAgent` 抽象类。
*   [x] 实现 ReAct (Reasoning + Acting) 提示词模板 (简化版实现).
*   [x] 实现 `Thinking` 状态管理.
*   [x] **验证**: Agent 能接收问题，并输出一段包含“思考过程”的日志 (通过 `/agent/run` 验证).

### Phase 4: Tool Infrastructure (工具系统地基)
> **目标**: 让 Agent 具备使用工具的能力 (Function Calling)。
*   [ ] 定义 `Tool` 接口与注解 (`@ToolDef`).
*   [ ] 实现工具注册中心 (`ToolRegistry`).
*   [ ] 实现 Demo 工具: `CurrentTimeTool` (获取当前时间)。
*   [ ] **验证**: 询问“现在几点了”，Agent 能自动调用工具并回答正确时间。

### Phase 5: File System & Workspace (文件能力)
> **目标**: 赋予 Agent 读写文件的能力（这是 JManus 的核心能力之一）。
*   [ ] 实现 `WorkspaceManager`: 管理安全的工作目录。
*   [ ] 实现核心文件工具: `WriteFile`, `ReadFile`, `ListFiles`.
*   [ ] **验证**: 指令 Agent “在当前目录创建一个 hello.txt 写入你好”，并验证文件存在。

### Phase 6: Browser Automation (浏览器能力)
> **目标**: 赋予 Agent “眼睛”，能浏览网页。
*   [ ] 引入 Playwright 依赖。
*   [ ] 封装 `BrowserService`: 管理浏览器实例和上下文。
*   [ ] 实现 `VisitPage`, `GetPageContent` 工具。
*   [ ] **验证**: 指令 Agent “访问 baidu.com 并告诉我标题是什么”。

### Phase 7: Runtime Engine (运行时与规划)
> **目标**: 实现自动化的任务循环执行引擎。
*   [ ] 实现 `PlanExecutor`: 负责“思考-调用工具-获取结果-再思考”的死循环逻辑。
*   [ ] 实现 `ExecutionRecorder`: 记录每一步的执行快照。
*   [ ] **验证**: 处理多步骤任务（如“搜索关于 Java 的新闻，并保存摘要到文件”）。

### Phase 8: Frontend Integration (UI 集成)
> **目标**: 将后端能力可视化。
*   [ ] 复制 `ui-vue3` 源码到 `myManus/ui`.
*   [ ] 适配前端 API 调用地址。
*   [ ] 后端实现 SSE (Server-Sent Events) 接口，推送实时执行流。
*   [ ] **验证**: 在浏览器中看到 Agent 逐字打印思考过程，并实时展示工具调用结果。

### Phase 9: Advanced & Polish (进阶完善)
*   [ ] 集成 MCP (Model Context Protocol).
*   [ ] 实现 `MemoryService` (对话上下文记忆).
*   [ ] 完善异常处理与重试机制.

---

**Current Status**: Ready to start Phase 1.