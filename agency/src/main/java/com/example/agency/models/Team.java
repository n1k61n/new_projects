package com.example.agency.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "team_members")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String role;
    @Column(name = "image_url")
    private String imageUrl;
    @Column(name = "facebook_url")
    private String facebookUrl;
    @Column(name = "twitter_url")
    private String twitterUrl;
    @Column(name = "linkedin_url")
    private String linkedinUrl;



}
