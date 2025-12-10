package com.example.agency.dtos.responce;

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
public class AboutResponceDTO {
    private Long id;

    private LocalDateTime date;
    private String title;
    private String description;
    private String imageUrl;
}
