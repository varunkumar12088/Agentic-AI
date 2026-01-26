package com.learning.config;

import com.google.genai.Client;
import com.google.genai.types.ClientOptions;
import com.learning.properties.AIProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.google.genai.GoogleGenAiChatModel;
import org.springframework.ai.google.genai.GoogleGenAiChatOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class AIConfig {

    private final AIProperties aiProperties;

    @Bean("reasoningChatModel")
    public GoogleGenAiChatModel getChatModel() {
        ClientOptions clientOptions = ClientOptions.builder()
                .maxConnections(5)
                .maxConnectionsPerHost(2)
                .build();
        Client client = Client.builder()
                .apiKey(aiProperties.apiKey())
                .clientOptions(clientOptions)
                .build();

        return GoogleGenAiChatModel.builder()
                .defaultOptions(
                        GoogleGenAiChatOptions.builder()
                                .model(aiProperties.reasoning())
                                .build()
                )
                .genAiClient(client)
                .build();
    }
}
