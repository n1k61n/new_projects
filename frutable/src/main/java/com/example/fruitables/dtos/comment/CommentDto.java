package com.example.fruitables.dtos.comment;

import com.example.fruitables.models.Product;
import com.example.fruitables.models.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
    private Long id;
    private String comment;
    private Product product;
    private User user;
    private LocalDateTime createdAt;
    private Integer rating;
}
