package com.learning.execution;

import com.learning.constant.IntentType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PlanExecutionResult {

    private final String planId;
    private final IntentType intent;
    private final boolean success;

    // Short user-facing summary (used for aggregation)
    private final String summary;

    // Optional: full execution context (for audit/debug)
    private final ExecutionContext context;
}
