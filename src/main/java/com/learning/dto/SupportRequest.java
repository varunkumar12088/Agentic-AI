package com.learning.dto;

import com.learning.dto.plain.ExecutionPlan;
import com.learning.dto.plain.PlannedQuery;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @param originalMessage Original user message (for reference / fallback)
 * @param plans           All execution plans (1 per intent)
 */
public record SupportRequest(String sessionId, String originalMessage, List<ExecutionPlan> plans) {

    public PlannedQuery toPlannedQuery(ExecutionPlan plan) {

        return new PlannedQuery(
                this.sessionId,
                plan.getIntent(),
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

        // Best case: ExecutionPlan already stores rewritten query
        if (plan.getRewrittenQuery() != null) {
            return plan.getRewrittenQuery();
        }

        // Fallback: original user message
        return this.originalMessage;
    }
}
