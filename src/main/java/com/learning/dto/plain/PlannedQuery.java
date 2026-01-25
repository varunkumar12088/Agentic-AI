package com.learning.dto.plain;

import lombok.Data;

@Data
public class PlannedQuery {

    private String sessionId;
    private String intent;
    private String rewrittenQuery;
}
