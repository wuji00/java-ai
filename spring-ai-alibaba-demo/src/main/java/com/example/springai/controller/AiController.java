package com.example.springai.controller;

import com.example.springai.service.AiService;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Spring AI Alibaba 控制器
 * 
 * 提供REST API接口来访问AI服务的各种功能
 * 包括文本生成、聊天、翻译、总结等多种功能
 */
@RestController
@RequestMapping("/api/ai")
public class AiController {

    @Autowired
    private AiService aiService;

    /**
     * 基本文本生成API
     * 
     * @param request 包含提示文本的请求对象
     * @return 生成的文本内容
     */
    @PostMapping("/generate")
    public String generate(@RequestBody TextRequest request) {
        return aiService.generateText(request.getPrompt());
    }

    /**
     * 聊天对话API
     * 
     * @param request 包含历史消息和当前消息的请求对象
     * @return AI回复内容
     */
    @PostMapping("/chat")
    public String chat(@RequestBody ChatRequest request) {
        return aiService.chat(request.getMessages(), request.getCurrentMessage());
    }

    /**
     * 使用模板生成内容API
     * 
     * @param request 包含姓名和主题的请求对象
     * @return 生成的个性化内容
     */
    @PostMapping("/template")
    public String generateWithTemplate(@RequestBody TemplateRequest request) {
        return aiService.generateWithTemplate(request.getName(), request.getTopic());
    }

    /**
     * 文本总结API
     * 
     * @param request 包含待总结文本的请求对象
     * @return 总结后的内容
     */
    @PostMapping("/summarize")
    public String summarize(@RequestBody TextRequest request) {
        return aiService.summarizeText(request.getPrompt());
    }

    /**
     * 文本分类API
     * 
     * @param request 包含待分类文本和分类选项的请求对象
     * @return 分类结果
     */
    @PostMapping("/classify")
    public String classify(@RequestBody ClassificationRequest request) {
        return aiService.classifyText(request.getText(), request.getCategories());
    }

    /**
     * 情感分析API
     * 
     * @param request 包含待分析文本的请求对象
     * @return 情感分析结果
     */
    @PostMapping("/sentiment")
    public String analyzeSentiment(@RequestBody TextRequest request) {
        return aiService.analyzeSentiment(request.getPrompt());
    }

    /**
     * 文本翻译API
     * 
     * @param request 包含待翻译文本和目标语言的请求对象
     * @return 翻译后的文本
     */
    @PostMapping("/translate")
    public String translate(@RequestBody TranslationRequest request) {
        return aiService.translateText(request.getText(), request.getTargetLanguage());
    }

    /**
     * 文本改写API
     * 
     * @param request 包含待改写文本的请求对象
     * @return 改写后的文本
     */
    @PostMapping("/rewrite")
    public String rewrite(@RequestBody TextRequest request) {
        return aiService.rewriteText(request.getPrompt());
    }

    // 请求对象定义
    public static class TextRequest {
        private String prompt;

        public String getPrompt() {
            return prompt;
        }

        public void setPrompt(String prompt) {
            this.prompt = prompt;
        }
    }

    public static class ChatRequest {
        private List<Message> messages;
        private String currentMessage;

        public List<Message> getMessages() {
            return messages;
        }

        public void setMessages(List<Message> messages) {
            this.messages = messages;
        }

        public String getCurrentMessage() {
            return currentMessage;
        }

        public void setCurrentMessage(String currentMessage) {
            this.currentMessage = currentMessage;
        }
    }

    public static class TemplateRequest {
        private String name;
        private String topic;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTopic() {
            return topic;
        }

        public void setTopic(String topic) {
            this.topic = topic;
        }
    }

    public static class ClassificationRequest {
        private String text;
        private List<String> categories;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public List<String> getCategories() {
            return categories;
        }

        public void setCategories(List<String> categories) {
            this.categories = categories;
        }
    }

    public static class TranslationRequest {
        private String text;
        private String targetLanguage;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getTargetLanguage() {
            return targetLanguage;
        }

        public void setTargetLanguage(String targetLanguage) {
            this.targetLanguage = targetLanguage;
        }
    }
}