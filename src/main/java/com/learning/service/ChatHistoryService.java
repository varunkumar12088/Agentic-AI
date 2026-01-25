package com.learning.service;

import com.learning.dto.ChatMessage;

import java.util.List;

public interface ChatHistoryService {

    void recordUserMessage(String sessionId, String message);

    void recordAssistantMessage(String sessionId, String message);

    List<ChatMessage> getRecentHistory(String sessionId);

    void clearSession(String sessionId);
}
