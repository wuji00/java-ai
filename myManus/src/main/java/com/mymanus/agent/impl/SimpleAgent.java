package com.mymanus.agent.impl;

import com.mymanus.agent.AgentState;
import com.mymanus.agent.ReActAgent;
import com.mymanus.service.LlmService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class SimpleAgent extends ReActAgent {

    private static final Logger log = LoggerFactory.getLogger(SimpleAgent.class);

    public SimpleAgent(LlmService llmService) {
        super(llmService);
    }

    @Override
    public String getName() {
        return "SimpleAgent";
    }

    @Override
    public String getDescription() {
        return "A simple agent for verifying ReAct loop";
    }

    @Override
    protected boolean think() {
        log.info("Thinking...");
        // In a real agent, we would build a prompt with history and tools.
        // Here we just simulate a thought process.
        
        // Let's ask LLM to simulate a thought
        String prompt = "You are a ReAct agent. Current step is " + currentStep + ". " +
                "If step < 3, say 'I need to perform an action'. " +
                "If step >= 3, say 'Task Finished'.";
        
        String response = llmService.chat(prompt);
        log.info("LLM Thought: {}", response);

        if (response.toLowerCase().contains("finished")) {
            return false;
        }
        return true;
    }

    @Override
    protected CompletableFuture<AgentExecResult> act() {
        log.info("Acting...");
        // Simulate an action
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return CompletableFuture.completedFuture(new AgentExecResult("Action Performed", AgentState.IN_PROGRESS));
    }
}
