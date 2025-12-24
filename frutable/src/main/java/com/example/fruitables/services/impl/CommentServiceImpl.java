package com.example.fruitables.services.impl;

import com.example.fruitables.dtos.comment.CommentDto;
import com.example.fruitables.models.Comment;
import com.example.fruitables.repositories.CommentRepository;
import com.example.fruitables.services.CommentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final ModelMapper modelMapper;
    private final CommentRepository commentRepository;

    @Override
    public boolean create(CommentDto commentdto) {
        // Логика создания комментария
        if (commentdto != null) {
            Comment comment = modelMapper.map(commentdto, Comment.class);
            commentRepository.save(comment);
            return true;
        }
        return false;
    }
}

