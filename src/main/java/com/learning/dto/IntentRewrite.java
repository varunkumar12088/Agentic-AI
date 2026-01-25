package com.learning.dto;

import com.learning.domain.intent.IntentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.checkerframework.checker.units.qual.N;

@Data
@AllArgsConstructor
@N
public class IntentRewrite {

    private IntentType intent;
    private String rewrittenQuery;
    private String queryHeading;
    private double confidence;

}
