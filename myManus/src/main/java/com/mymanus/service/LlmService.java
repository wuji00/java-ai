package com.mymanus.service;

import org.springframework.ai.chat.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class LlmService {

    private final ChatClient chatClient;

    public LlmService(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    public String chat(String prompt) {
        return chatClient.call(prompt);
    }
}