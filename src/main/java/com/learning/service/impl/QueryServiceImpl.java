package com.learning.service.impl;

import com.learning.agents.IntentDetectionAgent;
import com.learning.agents.QueryPlanningAgent;
import com.learning.domain.model.UserQuery;
import com.learning.dto.IntentDetectionResult;
import com.learning.dto.plain.ExecutionPlan;
import com.learning.dto.plain.PlannedQuery;
import com.learning.service.QueryService;
import com.learning.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sound.midi.Soundbank;

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

        return "";
    }

}
