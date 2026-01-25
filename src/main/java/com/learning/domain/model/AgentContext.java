package com.learning.domain.model;

import java.util.UUID;

public class AgentContext {

    private final UUID executionId;
    private final String correlationId;

    public AgentContext(String correlationId) {
        this.executionId = UUID.randomUUID();
        this.correlationId = correlationId;
    }

    public UUID getExecutionId() {
        return executionId;
    }

    public String getCorrelationId() {
        return correlationId;
    }
}
