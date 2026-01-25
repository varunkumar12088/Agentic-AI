package com.learning.service.impl;

import com.learning.dto.ChatMessage;
import com.learning.service.ChatHistoryService;
import com.learning.store.ChatHistoryStore;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class ChatHistoryServiceImpl implements ChatHistoryService {

    private final ChatHistoryStore store;

    public ChatHistoryServiceImpl(ChatHistoryStore store) {
        this.store = store;
    }

    @Override
    public void recordUserMessage(String sessionId, String message) {
        store.append(
                sessionId,
                new ChatMessage("USER", message, Instant.now())
        );
    }

    @Override
    public void recordAssistantMessage(String sessionId, String message) {
        store.append(
                sessionId,
                new ChatMessage("ASSISTANT", message, Instant.now())
        );
    }

    @Override
    public List<ChatMessage> getRecentHistory(String sessionId) {
        return store.getHistory(sessionId);
    }

    @Override
    public void clearSession(String sessionId) {
        store.clear(sessionId);
    }
}
