package com.learning.agents.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learning.agents.QueryPlanningAgent;
import com.learning.constant.AgentConstant;
import com.learning.dto.plain.ExecutionPlan;
import com.learning.dto.plain.PlannedQuery;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Component;

@Component
public class QueryPlanningAgentImpl implements QueryPlanningAgent {

    private final ChatClient chatClient;
    private final ObjectMapper mapper;

    public QueryPlanningAgentImpl(ChatClient.Builder builder, ObjectMapper mapper) {
        this.chatClient = builder.build();
        this.mapper = mapper;
    }

    @Override
    public ExecutionPlan createPlan(PlannedQuery input) {
        return generatePlan(buildPrompt(input));
    }

    @Override
    public ExecutionPlan regeneratePlan(PlannedQuery input, String failureReason) {
        String prompt = buildRegenerationPrompt(
                input, failureReason);
        return generatePlan(prompt);
    }

    private ExecutionPlan generatePlan(String prompt) {
        String response = chatClient
                .prompt(prompt)
                .call()
                .content();
        System.out.println("response ::" + response);
        try {
            return mapper.readValue(response, ExecutionPlan.class);
        } catch (Exception e) {
            throw new IllegalStateException(
                    "Invalid execution plan from LLM", e);
        }
    }

    private String buildPrompt(PlannedQuery input) {
        return  AgentConstant.QUERY_PLANING.formatted(input.getPrimaryIntent(), input.getIntents(), input.getQuery());
    }

    private String buildRegenerationPrompt(PlannedQuery input, String failureReason) {
        return AgentConstant.QUERY_PAINING_FAILED.formatted(failureReason, input.getQuery());
    }
}
