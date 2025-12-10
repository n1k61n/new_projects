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
public class TeamRequestDTO {
    private String name;        // Üzvün adı, məsələn: "Murad Aliyev"
    private String role;        // Vəzifə: "Lead Developer"

    private String imageUrl;    // Profil şəkilinin linki

    private String facebookUrl; // Sosial media linkləri

    private String twitterUrl;

    private String linkedinUrl;
}

