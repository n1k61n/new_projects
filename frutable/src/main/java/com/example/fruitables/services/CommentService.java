package com.example.fruitables.services;

import com.example.fruitables.dtos.comment.CommentDto;

public interface CommentService {
    boolean create(CommentDto comment);
}

