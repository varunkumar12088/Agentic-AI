package com.learning.dto;

import com.learning.domain.intent.IntentType;

import java.util.List;

public record IntentDetectionResult(
        String heading,
        IntentType primaryIntent,
        List<IntentRewrite> intentRewrites) { }
