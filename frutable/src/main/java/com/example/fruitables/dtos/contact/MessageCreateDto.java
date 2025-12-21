package com.example.fruitables.dtos.contact;


import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MessageCreateDto {
    private String name;
    private String email;
    private String message;
    private boolean messaageRead;
    private LocalDateTime createdAt;
}