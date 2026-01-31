package com.learning.dto.plain;

import com.learning.domain.intent.IntentType;
import com.learning.dto.IntentDetectionResult;
import com.learning.dto.IntentRewrite;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlannedQuery {

    private String sessionId;
    private IntentType primaryIntent;
    private List<IntentType> intents;
    private String query;

    public static PlannedQuery toPlannedQuery(IntentDetectionResult detectionResult, String userQuery) {
        PlannedQuery plannedQuery = new PlannedQuery();
        plannedQuery.setSessionId("");
        plannedQuery.setQuery("");
        plannedQuery.setPrimaryIntent(detectionResult.primaryIntent());
        List<IntentRewrite> intentRewrites = detectionResult.intentRewrites();
        List<IntentType> intentTypes = new ArrayList<>();

        for(IntentRewrite intentRewrite : intentRewrites){
            if(intentRewrite.getIntent().equals(IntentType.COMPLAINT)){
                plannedQuery.setQuery(intentRewrite.getRewrittenQuery());
                continue;
            }
            intentTypes.add(intentRewrite.getIntent());
        }

        if(StringUtils.isBlank(plannedQuery.getQuery())){
            plannedQuery.setQuery(userQuery);
        }

        return plannedQuery;
    }
}
