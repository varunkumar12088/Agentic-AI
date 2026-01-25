package com.learning.agents;

import com.learning.dto.plain.ExecutionPlan;
import com.learning.dto.plain.PlannedQuery;

public interface QueryPlanningAgent {

    ExecutionPlan createPlan(PlannedQuery input);

    ExecutionPlan regeneratePlan(PlannedQuery input, String failureReason);
}
