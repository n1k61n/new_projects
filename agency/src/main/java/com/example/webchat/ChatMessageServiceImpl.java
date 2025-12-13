package com.example.webchat;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatMessageServiceImpl implements ChatMessageService {

    @Autowired
    private ChatMessageRepository repository;
    @Autowired
    private ChatRoomService chatRoomService;


    @Override
    public ChatMessage save(ChatMessage chatMessage) {
        chatMessage.setStatus(MessageStatus.RECEIVED);
        repository.save(chatMessage);
        return chatMessage;
    }

    @Override
    public Long countNewMessages(String senderId, String recipientId) {
        return repository.countBySenderIdAndRecipientIdAndStatus(
                senderId, recipientId, MessageStatus.RECEIVED);
    }

    @Override
    public List<ChatMessage> findChatMessages(String senderId, String recipientId) {
        var chatId = chatRoomService.getChatId(senderId, recipientId, false);

        var messages =
                chatId.map(cId -> repository.findByChatId(cId)).orElse(new ArrayList<>());

        if(messages.size() > 0) {
            updateStatuses(senderId, recipientId, MessageStatus.DELIVERED);
        }

        return messages;
    }

    @Override
    public ChatMessage findById(String id) {
        return repository
                .findById(id)
                .map(chatMessage -> {
                    chatMessage.setStatus(MessageStatus.DELIVERED);
                    return repository.save(chatMessage);
                })
                .orElseThrow(() ->
                        new ResourceNotFoundException("Event cant find message (" + id + ")"));
    }

    @Override
    public boolean updateStatuses(String senderId, String recipientId, MessageStatus status) {
        var chatId = chatRoomService.getChatId(senderId, recipientId, false);

        if(chatId.isPresent()) {
            if(repository.updateStatusById(chatId.get(), status) > 0) {
                return true;
            }
        }
        return false;
    }


    // Inside your Service class
    public void updateStatus(Long id, String newStatus) {
        repository.findById(String.valueOf(id)).ifPresent(message -> {
            message.setStatus(MessageStatus.valueOf(newStatus));
            repository.save(message); // Spring handles the update automatically
        });
    }

    @Override
    public List<String> findChatUsers(String adminId) {
        // Find all unique senderId's where recipientId is adminId
        List<String> senderIds = repository.findDistinctSenderIdByRecipientId(adminId);
        // Find all unique recipientId's where senderId is adminId
        List<String> recipientIds = repository.findDistinctRecipientIdBySenderId(adminId);

        // Combine and get unique user IDs, excluding the admin's own ID
        List<String> chatUsers = new ArrayList<>();
        chatUsers.addAll(senderIds);
        chatUsers.addAll(recipientIds);

        return chatUsers.stream()
                .distinct()
                .filter(id -> !id.equals(adminId))
                .collect(java.util.stream.Collectors.toList());
    }
}