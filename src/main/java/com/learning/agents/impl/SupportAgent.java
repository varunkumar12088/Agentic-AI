package com.learning.agents.impl;

import com.learning.agents.Agent;
import com.learning.agents.QueryPlanningAgent;
import com.learning.agents.SpecializedAgent;
import com.learning.dto.SupportRequest;
import com.learning.dto.SupportResponse;
import com.learning.dto.plain.ExecutionPlan;
import com.learning.dto.plain.PlanStep;
import com.learning.execution.ExecutionContext;
import com.learning.execution.PlanExecutionResult;
import com.learning.execution.StepResult;
import com.learning.registry.AgentRegistry;
import com.learning.util.DataParseUtil;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SupportAgent implements Agent<SupportRequest, SupportResponse>  {

    private final AgentRegistry registry;
    private final QueryPlanningAgent planningAgent;

    public SupportAgent(AgentRegistry registry, QueryPlanningAgent planningAgent) {
        this.registry = registry;
        this.planningAgent = planningAgent;
    }


    @Override
    public SupportResponse execute(SupportRequest request) {
        List<PlanExecutionResult> results = new ArrayList<>();

        for (ExecutionPlan plan : request.plans()) {

            ExecutionContext context = new ExecutionContext();
            context.setSessionId(request.sessionId());
            context.setIntent(plan.getIntent());
            context.setPlanId(plan.getPlanId());

            boolean failed = false;

            for (PlanStep step : plan.getSteps()) {

                SpecializedAgent agent = registry.get(step.getTarget());

                StepResult stepResult = agent.execute(context, step);

                if (!stepResult.isSuccess()) {
                    failed = true;

                    if (step.isMandatory()) {
                        ExecutionPlan newPlan =
                                planningAgent.regeneratePlan(
                                        request.toPlannedQuery(plan),
                                        stepResult.getMessage()
                                );
                        return execute(request.withPlan(newPlan));
                    }
                    break;
                }

                context.getAttributes().put(
                        step.getStepNumber() + "",
                        stepResult.getData()
                );
            }

            results.add(new PlanExecutionResult(
                    plan.getPlanId(),
                    DataParseUtil.parseIntent(plan.getIntent()),
                    !failed,
                    "",
                    context
            ));
        }

        return aggregate(results);
    }

    private SupportResponse aggregate(
            List<PlanExecutionResult> results) {

        boolean allSuccessful = results.stream()
                .allMatch(PlanExecutionResult::isSuccess);

        StringBuilder response = new StringBuilder();

        // Header
        if (allSuccessful) {
            response.append("Here’s what I found:\n\n");
        } else {
            response.append(
                    "I’ve partially resolved your request. Details below:\n\n"
            );
        }

        // Per-intent summaries
        for (PlanExecutionResult result : results) {
            response.append("• ")
                    .append(result.getSummary())
                    .append("\n");
        }

        // Footer (optional, user-friendly)
        if (!allSuccessful) {
            response.append(
                    "\nSome issues may need further review. "
                            + "Let me know if you want me to continue or connect you with support."
            );
        }

        return new SupportResponse(
                response.toString().trim(),
                allSuccessful
        );
    }
}
