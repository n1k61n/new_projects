package com.example.fruitables.models;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "contact_messages")
@Data
public class ContactMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name; // Your Name

    @Column(nullable = false, length = 100)
    private String email; // Enter Your Email

    @Column(nullable = false, columnDefinition = "TEXT")
    private String message; // Your Message

    @Column(name = "message_read", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean messaageRead;

    private LocalDateTime createdAt = LocalDateTime.now();
}