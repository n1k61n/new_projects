package com.example.coffo.DTOs.responce;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BlogResponceDTO {
    private Long id;
    private String title;
    private String description;
    private String imageUrl;
    private LocalDateTime createdAt;
}
