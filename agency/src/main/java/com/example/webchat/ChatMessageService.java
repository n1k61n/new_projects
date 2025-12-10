package com.example.webchat;

import java.util.List;


public interface ChatMessageService {
    ChatMessage save(ChatMessage chatMessage);

    Long countNewMessages(String senderId, String recipientId);

    List<ChatMessage> findChatMessages(String senderId, String recipientId);

    ChatMessage findById(String id);

    boolean updateStatuses(String senderId, String recipientId, MessageStatus status);


}
