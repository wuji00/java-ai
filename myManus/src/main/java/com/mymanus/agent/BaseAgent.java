package com.mymanus.agent;

import com.mymanus.service.LlmService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public abstract class BaseAgent {

    private static final Logger log = LoggerFactory.getLogger(BaseAgent.class);

    protected LlmService llmService;
    protected int maxSteps = 10;
    protected int currentStep = 0;

    public BaseAgent(LlmService llmService) {
        this.llmService = llmService;
    }

    public abstract String getName();
    public abstract String getDescription();

    public CompletableFuture<AgentExecResult> run() {
        currentStep = 0;
        List<AgentExecResult> results = new ArrayList<>();
        return runStepRecursive(1, results);
    }

    private CompletableFuture<AgentExecResult> runStepRecursive(int stepNum, List<AgentExecResult> results) {
        if (stepNum > maxSteps) {
            log.info("Agent reached max rounds ({})", maxSteps);
            AgentExecResult finalResult = new AgentExecResult("Max steps reached", AgentState.COMPLETED);
            results.add(finalResult);
            return CompletableFuture.completedFuture(new AgentExecResult(finalResult.getResult(), finalResult.getState(), results));
        }

        currentStep = stepNum;
        log.info("Executing round {}/{}", currentStep, maxSteps);

        return step().thenCompose(stepResult -> {
            AgentState stepState = stepResult.getState();
            if (stepState == AgentState.COMPLETED || stepState == AgentState.FAILED) {
                log.info("Agent execution {} at round {}", stepState, currentStep);
                results.add(stepResult);
                return CompletableFuture.completedFuture(new AgentExecResult(stepResult.getResult(), stepResult.getState(), results));
            }

            results.add(stepResult);
            return runStepRecursive(stepNum + 1, results);
        });
    }

    protected abstract CompletableFuture<AgentExecResult> step();

    public static class AgentExecResult {
        private String result;
        private AgentState state;
        private List<AgentExecResult> results;

        public AgentExecResult(String result, AgentState state) {
            this.result = result;
            this.state = state;
            this.results = new ArrayList<>();
        }

        public AgentExecResult(String result, AgentState state, List<AgentExecResult> results) {
            this.result = result;
            this.state = state;
            this.results = results != null ? new ArrayList<>(results) : new ArrayList<>();
        }

        public String getResult() { return result; }
        public AgentState getState() { return state; }
        public List<AgentExecResult> getResults() { return results; }
        
        @Override
        public String toString() {
            return "Result: " + result + ", State: " + state;
        }
    }
}
