package com.example.springai.service;

import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Spring AI Alibaba 服务类
 * 
 * 该服务类演示了Spring AI Alibaba的各种功能，包括：
 * - 基本文本生成
 * - 聊天对话
 * - 提示模板使用
 * - 文本分割
 * - 自定义提示工程
 */
@Service
public class AiService {

    @Autowired
    private ChatModel chatModel;

    /**
     * 基本文本生成功能
     * 使用通义千问模型生成文本内容
     * 
     * @param prompt 用户输入的提示文本
     * @return 生成的文本内容
     */
    public String generateText(String prompt) {
        // 创建提示并发送给模型
        Prompt userPrompt = new Prompt(new UserMessage(prompt));
        ChatResponse response = chatModel.call(userPrompt);
        
        // 返回生成的文本内容
        return response.getResult().getOutput().getText();
    }

    /**
     * 聊天对话功能
     * 支持连续的对话交互
     * 
     * @param messages 历史消息列表
     * @param currentMessage 当前用户消息
     * @return AI回复内容
     */
    public String chat(List<Message> messages, String currentMessage) {
        // 将当前消息添加到历史消息列表
        messages.add(new UserMessage(currentMessage));
        
        // 创建包含历史消息的提示
        Prompt prompt = new Prompt(messages);
        ChatResponse response = chatModel.call(prompt);
        
        return response.getResult().getOutput().getText();
    }

    /**
     * 使用提示模板的功能
     * 展示如何使用模板生成结构化内容
     * 
     * @param name 姓名
     * @param topic 主题
     * @return 生成的个性化内容
     */
    public String generateWithTemplate(String name, String topic) {
        // 创建提示模板
        PromptTemplate promptTemplate = new PromptTemplate(
            "请为用户 {name} 写一篇关于 {topic} 的简短介绍。要求内容生动有趣，长度在100字以内。"
        );
        
        // 使用模板和参数创建提示
        Map<String, Object> model = Map.of("name", name, "topic", topic);
        Prompt prompt = promptTemplate.create(model);
        
        // 调用模型生成内容
        ChatResponse response = chatModel.call(prompt);
        return response.getResult().getOutput().getText();
    }

    /**
     * 文本内容总结功能
     * 对长文本进行摘要总结
     * 
     * @param text 需要总结的文本
     * @return 总结后的内容
     */
    public String summarizeText(String text) {
        PromptTemplate promptTemplate = new PromptTemplate(
            "请对以下文本进行总结，要求保留核心信息，字数控制在原文的30%以内：\n\n{text}"
        );
        
        Map<String, Object> model = Map.of("text", text);
        Prompt prompt = promptTemplate.create(model);
        
        ChatResponse response = chatModel.call(prompt);
        return response.getResult().getOutput().getContent();
    }

    /**
     * 文本分类功能
     * 对文本进行分类标注
     * 
     * @param text 待分类的文本
     * @param categories 分类选项
     * @return 分类结果
     */
    public String classifyText(String text, List<String> categories) {
        String categoriesStr = String.join(", ", categories);
        PromptTemplate promptTemplate = new PromptTemplate(
            "请将以下文本分类到以下类别之一：{categories}\n\n文本内容：{text}\n\n分类结果："
        );
        
        Map<String, Object> model = Map.of(
            "text", text,
            "categories", categoriesStr
        );
        Prompt prompt = promptTemplate.create(model);
        
        ChatResponse response = chatModel.call(prompt);
        return response.getResult().getOutput().getContent();
    }

    /**
     * 情感分析功能
     * 分析文本的情感倾向
     * 
     * @param text 待分析的文本
     * @return 情感分析结果
     */
    public String analyzeSentiment(String text) {
        PromptTemplate promptTemplate = new PromptTemplate(
            "请分析以下文本的情感倾向（正面、负面或中性），并简要说明理由：\n\n{text}\n\n情感分析结果："
        );
        
        Map<String, Object> model = Map.of("text", text);
        Prompt prompt = promptTemplate.create(model);
        
        ChatResponse response = chatModel.call(prompt);
        return response.getResult().getOutput().getContent();
    }

    /**
     * 文本翻译功能
     * 将文本从一种语言翻译到另一种语言
     * 
     * @param text 待翻译的文本
     * @param targetLanguage 目标语言
     * @return 翻译后的文本
     */
    public String translateText(String text, String targetLanguage) {
        PromptTemplate promptTemplate = new PromptTemplate(
            "请将以下文本翻译成{targetLanguage}：\n\n{text}\n\n翻译结果："
        );
        
        Map<String, Object> model = Map.of(
            "text", text,
            "targetLanguage", targetLanguage
        );
        Prompt prompt = promptTemplate.create(model);
        
        ChatResponse response = chatModel.call(prompt);
        return response.getResult().getOutput().getContent();
    }

    /**
     * 文本改写功能
     * 保持原意的前提下改写文本
     * 
     * @param text 待改写的文本
     * @return 改写后的文本
     */
    public String rewriteText(String text) {
        PromptTemplate promptTemplate = new PromptTemplate(
            "请改写以下文本，保持原意但使用不同的表达方式：\n\n{text}\n\n改写结果："
        );
        
        Map<String, Object> model = Map.of("text", text);
        Prompt prompt = promptTemplate.create(model);
        
        ChatResponse response = chatModel.call(prompt);
        return response.getResult().getOutput().getContent();
    }
}