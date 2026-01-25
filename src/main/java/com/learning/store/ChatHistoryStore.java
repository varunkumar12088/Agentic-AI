package com.learning.store;

import com.learning.dto.ChatMessage;

import java.util.List;

public interface ChatHistoryStore {

    void append(String sessionId, ChatMessage message);

    List<ChatMessage> getHistory(String sessionId);

    void clear(String sessionId);

}
