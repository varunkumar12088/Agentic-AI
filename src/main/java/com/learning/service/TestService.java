package com.learning.service;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    private ChatClient chatClient;

    public TestService(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    public String testing(String query){
        return chatClient.prompt(query)
                .call()
                .content();
    }
}
