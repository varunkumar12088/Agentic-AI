package com.learning.agents;

import com.learning.dto.plain.PlanStep;
import com.learning.execution.ExecutionContext;
import com.learning.execution.StepResult;

public interface SpecializedAgent {

    StepResult execute(ExecutionContext context, PlanStep step);
}
