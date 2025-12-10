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
public class TeamResponceDTO {
    private Long id;

    private String name;
    private String role;

    private String imageUrl;

    private String facebookUrl;

    private String twitterUrl;

    private String linkedinUrl;
}
