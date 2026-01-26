package com.learning.service;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.google.genai.GoogleGenAiChatModel;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestService {

    private final GoogleGenAiChatModel chatClient;


    public String testing(String query){
        Prompt prompt = Prompt.builder()
                .content(query)
                .build();
        return chatClient
                .call(prompt)
                .getResult()
                .getOutput()
                .getText();
    }
}
