package com.example.agency.dtos.request;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PortfolioRequestDTO {
    private String title;
    private String description;

    private String imageUrl;
    private String category;
}

