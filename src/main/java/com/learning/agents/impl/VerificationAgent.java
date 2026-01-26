package com.learning.agents.impl;

import com.learning.agents.SpecializedAgent;
import com.learning.domain.intent.IntentType;
import com.learning.dto.plain.PlanStep;
import com.learning.execution.ExecutionContext;
import com.learning.execution.StepResult;
import org.springframework.stereotype.Component;

@Component
public class VerificationAgent implements SpecializedAgent {

    @Override
    public IntentType supportedIntentType() {
        return IntentType.VERIFICATION;
    }

    @Override
    public StepResult execute(ExecutionContext context, PlanStep step) {
        return null;
    }
}
