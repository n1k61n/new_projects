package com.example.agency.services;

import com.example.agency.models.ChatMessage;
import com.example.agency.enums.MessageStatus;

import java.util.List;


public interface ChatMessageService {
    ChatMessage save(ChatMessage chatMessage);

    Long countNewMessages(String senderId, String recipientId);

    List<ChatMessage> findChatMessages(String senderId, String recipientId);

    ChatMessage findById(String id);

    boolean updateStatuses(String senderId, String recipientId, MessageStatus status);

    List<String> findChatUsers(String adminId);


}
