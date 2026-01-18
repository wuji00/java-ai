package com.mymanus.controller;

import com.mymanus.service.LlmService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

@RestController
public class HelloController {

    private final LlmService llmService;

    public HelloController(LlmService llmService) {
        this.llmService = llmService;
    }

    @GetMapping("/hello")
    public Map<String, String> hello() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Hello from myManus!");
        response.put("status", "Phase 1 Complete");
        return response;
    }

    @GetMapping("/chat")
    public String chat(@RequestParam(defaultValue = "Tell me a joke") String prompt) {
        return llmService.chat(prompt);
    }
}
