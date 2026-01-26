package com.learning.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "ai.gemini")
public record AIProperties(
        String apiKey,
        String reasoning,
        String context) { }
