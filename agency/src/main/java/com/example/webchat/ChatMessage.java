package com.example.webchat;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "chat_messages")
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "chat_id")
    private String chatId;
    @Column(name = "sender_id")
    private String senderId;
    @Column(name = "recipient_id")
    private String recipientId;
    @Column(name = "sender_name")
    private String senderName;
    @Column(name = "recipient_name")
    private String recipientName;
    @Column(name = "messages")
    private String message;
    @Column(name = "timestamp")
    private Date timestamp;
    @Column(name = "status")
    private MessageStatus status;

}