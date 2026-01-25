package com.learning.store.impl;

import com.learning.domain.entity.ChatHistoryEntity;
import com.learning.dto.ChatMessage;
import com.learning.repository.ChatHistoryRepository;
import com.learning.store.ChatHistoryStore;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ChatHistoryStoreImpl implements ChatHistoryStore {

    private final ChatHistoryRepository repository;

    public ChatHistoryStoreImpl(ChatHistoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public void append(String sessionId, ChatMessage message) {
        ChatHistoryEntity entity = new ChatHistoryEntity();
        entity.setSessionId(sessionId);
        entity.setRole(message.getRole());
        entity.setContent(message.getContent());
        repository.save(entity);
    }

    @Override
    public List<ChatMessage> getHistory(String sessionId) {
        return repository
                .findTop20BySessionIdOrderByCreatedAtAsc(sessionId)
                .stream()
                .map(e -> new ChatMessage(
                        e.getRole(),
                        e.getContent(),
                        e.getCreatedAt()
                ))
                .toList();
    }

    @Override
    public void clear(String sessionId) {
        repository.deleteBySessionId(sessionId);
    }
}
