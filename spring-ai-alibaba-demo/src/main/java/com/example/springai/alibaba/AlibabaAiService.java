package com.example.springai.alibaba;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.model.ChatOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 阿里巴巴AI服务模拟实现
 * 
 * 这是一个模拟实现，用于演示Spring AI Alibaba的使用方式。
 * 在实际部署时，这里将替换为真正的通义千问服务。
 */
@Service
public class AlibabaAiService {

    @Autowired
    private ChatModel chatModel;

    /**
     * 生成文本内容
     * 
     * @param prompt 提示文本
     * @return 生成的文本内容
     */
    public String generate(String prompt) {
        Prompt userPrompt = new Prompt(new UserMessage(prompt));
        ChatResponse response = chatModel.call(userPrompt);
        return response.getResult().getOutput().getContent();
    }

    /**
     * 生成聊天回复
     * 
     * @param messages 消息列表
     * @return 聊天回复内容
     */
    public String chat(List<Message> messages) {
        Prompt prompt = new Prompt(messages);
        ChatResponse response = chatModel.call(prompt);
        return response.getResult().getOutput().getContent();
    }

    /**
     * 获取模型配置信息
     * 
     * @return 模型配置信息
     */
    public String getModelInfo() {
        // 这里模拟返回通义千问模型信息
        return "通义千问模型 (Qwen) - 阿里巴巴自主研发的超大规模语言模型";
    }
}