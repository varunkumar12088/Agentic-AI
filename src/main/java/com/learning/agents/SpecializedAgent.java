package com.learning.agents;

import com.learning.constant.IntentType;
import com.learning.dto.plain.PlanStep;
import com.learning.execution.ExecutionContext;
import com.learning.execution.StepResult;

public interface SpecializedAgent {

    IntentType supportedIntentType();

    StepResult execute(ExecutionContext context, PlanStep step);
}
