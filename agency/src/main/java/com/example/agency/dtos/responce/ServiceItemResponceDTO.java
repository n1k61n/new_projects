package com.example.agency.dtos.responce;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ServiceItemResponceDTO {
    private Long id;

    private String title;
    private String description;
    private String icon;

}
