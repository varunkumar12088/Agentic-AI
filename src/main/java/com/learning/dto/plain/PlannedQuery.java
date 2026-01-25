package com.learning.dto.plain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlannedQuery {

    private String sessionId;
    private String intent;
    private String rewrittenQuery;
}
