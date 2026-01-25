package com.learning.domain.model;

public class AgentResponse {

    private final String content;
    private final String agentName;
    private final boolean success;

    public AgentResponse(String content, String agentName, boolean success) {
        this.content = content;
        this.agentName = agentName;
        this.success = success;
    }

    public static AgentResponse success(String content, String agentName) {
        return new AgentResponse(content, agentName, true);
    }

    public static AgentResponse failure(String content, String agentName) {
        return new AgentResponse(content, agentName, false);
    }

    public String getContent() {
        return content;
    }

    public String getAgentName() {
        return agentName;
    }

    public boolean isSuccess() {
        return success;
    }
}
