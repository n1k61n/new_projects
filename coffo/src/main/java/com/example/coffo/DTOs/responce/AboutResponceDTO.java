package com.example.coffo.DTOs.responce;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AboutResponceDTO {
    private Long id;
    private String mainTitle;
    private String subTitle;
    private String imageUrl;
    private String description;
}