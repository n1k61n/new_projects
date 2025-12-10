package com.example.agency.dtos.request;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AboutRequestDTO {
    private LocalDateTime date;
    private String title;
    private String description;
    private String imageUrl;
}


