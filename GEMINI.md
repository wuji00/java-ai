# GEMINI.md - Lynxe (JManus) Project Context

## Project Overview

**Lynxe** (formerly JManus) is a Java implementation of **Manus**, a multi-agent collaboration system. It handles exploratory tasks requiring determinism, such as data mining and log analysis. It is built on **Spring AI Alibaba** and natively supports the **Model Context Protocol (MCP)**.

*   **Primary Language:** Java 17+
*   **Framework:** Spring Boot 3.x
*   **Frontend:** Vue 3 + Vite + TypeScript (located in `ui-vue3`)
*   **Architecture:** Monolithic Spring Boot application with an embedded or standalone Vue frontend.

## Tech Stack & Dependencies

### Backend (`/`)
*   **Build Tool:** Maven (Wrapper `mvnw` available)
*   **Key Frameworks:**
    *   Spring Boot 3.5.6
    *   Spring AI Alibaba (Snapshot versions)
    *   Spring AI (OpenAI, MCP Client)
    *   Spring Data JPA
*   **Database:** H2 (Default), MySQL, PostgreSQL (Configurable via profiles)
*   **Utilities:** Hutool, Guava, Gson, Jsoup, Playwright (Browser Automation), Apache POI/PDFBox (Document processing)

### Frontend (`/ui-vue3`)
*   **Build Tool:** pnpm (managed via `package.json`)
*   **Framework:** Vue 3 (Composition API)
*   **Bundler:** Vite
*   **UI Library:** Ant Design Vue
*   **State Management:** Pinia
*   **Languages:** TypeScript, Less

## Building & Running

### Prerequisites
*   **Java:** JDK 17 or higher
*   **Node.js:** v16+ (v22 recommended in Makefiles)
*   **Docker:** (Optional, for containerized deployment)
*   **Maven Daemon (`mvnd`):** Recommended by `Makefile` for faster builds, but standard `mvn` works.

### Quick Commands

| Task | Command | Description |
| :--- | :--- | :--- |
| **Backend Run** | `./mvnw spring-boot:run` | Starts Spring Boot app on port 18080. |
| **Backend Build** | `./mvnw clean package -DskipTests` | Builds the JAR file in `target/`. |
| **Frontend Run** | `cd ui-vue3 && pnpm dev` | Starts Vite dev server (usually port 5173). |
| **Frontend Build** | `cd ui-vue3 && pnpm build` | Builds static assets to `dist/`. |
| **Full Build** | `make build` | Uses `mvnd` to package the backend. |
| **Tests** | `make test` | Runs backend tests using `mvnd`. |

### Docker
*   **Build:** `docker build -t lynxe:latest .`
*   **Run:** `docker run -p 18080:18080 lynxe:latest`

## Development Conventions

### Code Style
*   **Java:** Enforced via `spotless` and `spring-javaformat`.
    *   **Fix Formatting:** `mvn spotless:apply` or `make spotless-apply`
    *   **Check Style:** `mvn checkstyle:check`
*   **Frontend:** Enforced via `eslint` and `prettier`.
    *   **Fix:** `pnpm lint` and `pnpm format` inside `ui-vue3`.

### Git Commit Guidelines
Follow the conventional commits format:
`type(module): description`
*   **Types:** `feat`, `fix`, `docs`, `style`, `refactor`, `test`, `chore`
*   **Example:** `feat(agent): add new data mining capability`

### Testing
*   **Backend:** JUnit 5. Run with `mvn test`.
*   **Frontend:** Vitest (Unit) and Cypress (E2E). Run with `pnpm test:unit` or `pnpm test:e2e`.

## Agent Interaction

*   **Language:** Always respond to the user in **Chinese (中文)**.

## Key Directories
*   `src/main/java`: Backend source code.
*   `ui-vue3`: Frontend source code.
*   `tools/make`: Makefile includes for different tasks.
*   `deploy`: Dockerfile and deployment scripts.
*   `knowledge`: Project specific documentation/notes (e.g., `toolcallId-flow.md`).
