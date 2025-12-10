package com.example.coffo.DTOs.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AboutRequestDTO {
    private String mainTitle;
    private String subTitle;
    private String imageUrl;
    private String description;
}

