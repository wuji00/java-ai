package com.example.springai.config;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ai.model.ApiKey;

/**
 * AI服务配置类
 * 
 * 配置AI服务相关的Bean，包括ChatModel等。
 * 在实际部署时，可以替换为通义千问或其他AI服务的配置。
 */
@Configuration
public class AiConfig {

    @Value("${spring.ai.openai.api-key:sk-1234567890}")
    private String openaiApiKey;

    @Value("${spring.ai.openai.base-url:https://api.openai.com/v1}")
    private String baseUrl;

    /**
     * 配置ChatModel Bean
     * 在实际使用中，这里应该是通义千问的配置
     * 
     * @return ChatModel实例
     */
    @Bean
    public ChatModel chatModel() {
        // 注意：这里使用OpenAI作为示例，实际使用时应替换为通义千问配置
        OpenAiApi openAiApi = new OpenAiApi(baseUrl, ApiKey.from(openaiApiKey));
        OpenAiChatOptions chatOptions = OpenAiChatOptions.builder()
                .withModel("gpt-3.5-turbo") // 在实际部署时替换为通义千问模型名称
                .build();
        
        return new OpenAiChatModel(openAiApi, chatOptions);
    }
}