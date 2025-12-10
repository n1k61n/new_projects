package com.example.agency.dtos.responce;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventResponceDTO {
    private Long id;

    private String date;
    private String title;
    private String description;

    private String imagePath;
    private Boolean isRightAligned;
}
