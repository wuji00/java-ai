package com.mymanus.agent;

import com.mymanus.service.LlmService;
import java.util.concurrent.CompletableFuture;

public abstract class ReActAgent extends BaseAgent {

    public ReActAgent(LlmService llmService) {
        super(llmService);
    }

    protected abstract boolean think();

    protected abstract CompletableFuture<AgentExecResult> act();

    @Override
    protected CompletableFuture<AgentExecResult> step() {
        try {
            boolean shouldAct = think();
            if (!shouldAct) {
                // If no action needed, we might be done or just thinking
                // For simplified ReAct, usually we stop if no action is generated?
                // Or maybe we consider it 'Thinking Only' step.
                return CompletableFuture.completedFuture(
                        new AgentExecResult("Thinking complete - no action needed", AgentState.IN_PROGRESS)
                );
            }
            return act();
        } catch (Exception e) {
            return CompletableFuture.completedFuture(
                    new AgentExecResult("Error: " + e.getMessage(), AgentState.FAILED)
            );
        }
    }
}
