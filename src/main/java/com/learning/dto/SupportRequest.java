package com.learning.dto;

import com.learning.domain.intent.IntentType;
import com.learning.dto.plain.ExecutionPlan;
import com.learning.dto.plain.PlannedQuery;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public record SupportRequest(String sessionId, String originalMessage, List<ExecutionPlan> plans) {

    public PlannedQuery toPlannedQuery(ExecutionPlan plan) {

        return new PlannedQuery(
                this.sessionId,
                IntentType.BILLING,
                Arrays.asList(IntentType.BILLING, IntentType.CUSTOMER_SUPPORT),
                extractRewrittenQuery(plan)
        );
    }

    public SupportRequest withPlan(ExecutionPlan newPlan) {

        List<ExecutionPlan> updatedPlans = new ArrayList<>();

        for (ExecutionPlan plan : this.plans) {
            if (plan.getPlanId().equals(newPlan.getPlanId())) {
                updatedPlans.add(newPlan);
            } else {
                updatedPlans.add(plan);
            }
        }

        return new SupportRequest(
                this.sessionId,
                this.originalMessage,
                updatedPlans
        );
    }

    private String extractRewrittenQuery(ExecutionPlan plan) {

        if (plan.getRewrittenQuery() != null) {
            return plan.getRewrittenQuery();
        }

        return this.originalMessage;
    }
}
