package com.example.fruitables.services.impl;

import com.example.fruitables.dtos.comment.CommentDto;
import com.example.fruitables.models.Comment;
import com.example.fruitables.repositories.CommentRepository;
import com.example.fruitables.services.CommentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public List<CommentDto> findAll(Sort sort) {
        return commentRepository.findAll(sort).stream().map(comment -> modelMapper.map(comment, CommentDto.class)).toList();
    }

    @Override
    public boolean deleteComment(Long id) {
        // Логика удаления комментария
        Optional<Comment> comment = commentRepository.findById(id);
        if(comment.isPresent()) {
            commentRepository.delete(comment.get());
            return true;
        }
        return false;
    }


}

