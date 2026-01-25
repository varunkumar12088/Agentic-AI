package com.learning.agents.impl;

import com.learning.agents.SpecializedAgent;
import com.learning.dto.plain.PlanStep;
import com.learning.execution.ExecutionContext;
import com.learning.execution.StepResult;
import org.springframework.stereotype.Component;

@Component
public class BillingAgent implements SpecializedAgent {

    @Override
    public StepResult execute(ExecutionContext context, PlanStep step) {
        return null;
    }
}
