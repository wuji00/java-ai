package com.example.springai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring AI Alibaba 演示项目主应用类
 * 
 * 该应用演示了Spring AI Alibaba的各种功能，包括：
 * - 通义千问大语言模型集成
 * - 文本生成
 * - 聊天功能
 * - 嵌入向量
 * - 提示模板
 * - 函数调用等
 */
@SpringBootApplication
public class SpringAiAlibabaDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringAiAlibabaDemoApplication.class, args);
    }

}