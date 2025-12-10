package com.example.fruitables.dtos.comment;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentDashboardDto {
    private Long id;
    private String comment;
    private Integer rating;
}
