package com.learning.agents.impl;

import com.learning.agents.SpecializedAgent;
import com.learning.constant.IntentType;
import com.learning.dto.plain.PlanStep;
import com.learning.execution.ExecutionContext;
import com.learning.execution.StepResult;
import org.springframework.stereotype.Component;

@Component
public class NetworkAgent implements SpecializedAgent {

    @Override
    public IntentType supportedIntentType() {
        return IntentType.NETWORK;
    }

    @Override
    public StepResult execute(ExecutionContext context, PlanStep step) {
        return null;
    }
}
