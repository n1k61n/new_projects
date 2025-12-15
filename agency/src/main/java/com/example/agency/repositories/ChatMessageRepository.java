package com.example.agency.repositories;



import com.example.agency.models.ChatMessage;
import com.example.agency.enums.MessageStatus;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, String> {

    Long countBySenderIdAndRecipientIdAndStatus(
            String senderId, String recipientId, MessageStatus status);

    List<ChatMessage> findByChatId(String chatId);
    @Modifying
    @Transactional
    @Query("UPDATE ChatMessage c SET c.status = :status WHERE c.id = :id")
    int updateStatusById(String s, MessageStatus status);

    List<String> findDistinctSenderIdByRecipientId(String recipientId);

    List<String> findDistinctRecipientIdBySenderId(String senderId);
}