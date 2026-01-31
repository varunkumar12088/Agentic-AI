package com.learning.service.impl;

import com.learning.agents.IntentDetectionAgent;
import com.learning.agents.QueryPlanningAgent;
import com.learning.constant.Action;
import com.learning.domain.model.UserQuery;
import com.learning.dto.IntentDetectionResult;
import com.learning.dto.IntentRewrite;
import com.learning.dto.plain.ExecutionPlan;
import com.learning.dto.plain.PlanStep;
import com.learning.dto.plain.PlannedQuery;
import com.learning.service.QueryService;
import com.learning.util.JsonUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QueryServiceImpl implements QueryService {

    @Autowired
    private IntentDetectionAgent intentDetectionAgent;

    @Autowired
    private QueryPlanningAgent queryPlanningAgent;

    @Override
    public String executeQuery(String query) {
        UserQuery userQuery = new UserQuery("", query);
        IntentDetectionResult detectionResult = intentDetectionAgent.detect(userQuery);
        System.out.println(JsonUtil.toJson(detectionResult));
        System.out.println("================================================");
        PlannedQuery plannedQuery = PlannedQuery.toPlannedQuery(detectionResult, query);
        ExecutionPlan executionPlan = queryPlanningAgent.createPlan(plannedQuery);
        System.out.println(JsonUtil.toJson(executionPlan));
        System.out.println("============================================");
        updateQuery(detectionResult, executionPlan);
        System.out.println(JsonUtil.toJson(executionPlan));

        return "";
    }

    private void updateQuery(IntentDetectionResult detectionResult, ExecutionPlan executionPlan){
        List<IntentRewrite> intentRewrites = detectionResult.intentRewrites();
        List<PlanStep> steps = executionPlan.getSteps();
        for (PlanStep step : steps) {
            if(StringUtils.isNotBlank(step.getQuery())) {
                continue;
            }
            for(IntentRewrite intentRewrite : intentRewrites){
                if(step.getAction().equals(Action.CALL_AGENT)
                        && step.getTarget().equals(intentRewrite.getIntent())) {
                    step.setQuery(intentRewrite.getRewrittenQuery());
                }
            }
        }
    }

}
