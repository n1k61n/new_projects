package com.example.coffo.DTOs.request;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BlogRequestDTO {


    private String title;

    private String description;

    private String imageUrl;

    private LocalDateTime createdAt;
}
