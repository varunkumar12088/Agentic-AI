package com.learning.agents.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learning.agents.IntentDetectionAgent;
import com.learning.constant.AgentConstant;
import com.learning.domain.intent.IntentType;
import com.learning.domain.model.UserQuery;
import com.learning.dto.IntentDetectionResult;
import com.learning.dto.IntentRewrite;
import com.learning.util.DataParseUtil;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class IntentDetectionAgentImpl implements IntentDetectionAgent {

    private final ChatClient chatClient;
    private final ObjectMapper objectMapper;

    public IntentDetectionAgentImpl(ChatClient.Builder builder, ObjectMapper objectMapper) {
        this.chatClient = builder.build();
        this.objectMapper = objectMapper;
    }

    @Override
    public IntentDetectionResult detect(UserQuery query) {
        String response = chatClient
                .prompt(buildPrompt(query.getMessage()))
                .call()
                .content();
        try {
            JsonNode root = objectMapper.readTree(response);
            String heading = root.path("heading").asText();
            IntentType primaryIntent = DataParseUtil.parseIntent(root.path("primaryIntent").asText());

            List<IntentRewrite> rewrites = new ArrayList<>();

            for (JsonNode node : root.path("intents")) {
                IntentType intent = DataParseUtil.parseIntent(node.path("intent").asText());
                String rewrittenQuery = node.path("rewrittenQuery").asText();
                String queryHeading = node.path("queryHeading").asText();
                double confidence = node.path("confidence").asDouble(0.0);

                rewrites.add(new IntentRewrite(
                        intent,
                        rewrittenQuery,
                        queryHeading,
                        confidence
                ));
            }

            // Safety fallback
            if (rewrites.isEmpty()) {
                rewrites.add(new IntentRewrite(
                        IntentType.UNKNOWN,
                        query.getMessage(),
                        "",
                        0.0
                ));
                primaryIntent = IntentType.UNKNOWN;
            }

            return new IntentDetectionResult(heading, primaryIntent, rewrites);

        } catch (Exception e){
            return new IntentDetectionResult(
                    "Not Found",
                    IntentType.UNKNOWN,
                    List.of(new IntentRewrite(
                            IntentType.UNKNOWN,
                            query.getMessage(),
                            "",
                            0.0
                    ))
            );
        }
    }

    private String buildPrompt(String message) {
        return AgentConstant.INTENT_QUERY.formatted(message);
    }

}
