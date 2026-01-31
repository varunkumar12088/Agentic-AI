package com.learning.execution;

import lombok.Data;

import java.util.Map;

@Data
public class ExecutionContext {

    private String sessionId;
    private String planId;

    // Shared data between steps
    private Map<String, Object> attributes;
}
