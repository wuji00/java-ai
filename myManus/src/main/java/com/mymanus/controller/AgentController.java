package com.mymanus.controller;

import com.mymanus.agent.BaseAgent;
import com.mymanus.agent.impl.SimpleAgent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
public class AgentController {

    private final SimpleAgent simpleAgent;

    public AgentController(SimpleAgent simpleAgent) {
        this.simpleAgent = simpleAgent;
    }

    @GetMapping("/agent/run")
    public String runAgent() throws ExecutionException, InterruptedException {
        BaseAgent.AgentExecResult result = simpleAgent.run().get();
        return "Agent Execution Finished. Final Result: " + result.toString();
    }
}
