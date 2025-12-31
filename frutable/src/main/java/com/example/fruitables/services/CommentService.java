package com.example.fruitables.services;

import com.example.fruitables.dtos.comment.CommentDto;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface CommentService {
    boolean create(CommentDto comment);

    List<CommentDto> findAll(Sort sort);

    boolean deleteComment(Long id);

    long countComment();
}

