package com.example.coffo.services.impl;

import com.example.coffo.DTOs.responce.BlogResponceDTO;
import com.example.coffo.models.Blog;
import com.example.coffo.repositories.BlogRepository;
import com.example.coffo.services.BlogService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BlogServiceImpl implements BlogService {

    private final BlogRepository blogRepository;
    private final ModelMapper modelMapper;

    @Override
    public BlogResponceDTO getBlogById(Long id) {
        Optional<Blog> blogOptional = blogRepository.findById(id);
        if (blogOptional.isPresent()) {
            Blog blogEntity = blogOptional.get();
            return modelMapper.map(blogEntity, BlogResponceDTO.class);
        }
        return null;
    }

    @Override
    public BlogResponceDTO createBlog(BlogResponceDTO blogDTO) {
        Blog blog = modelMapper.map(blogDTO, Blog.class);
        Blog savedBlog = blogRepository.save(blog);
        return modelMapper.map(savedBlog, BlogResponceDTO.class);
    }


    @Override
    public BlogResponceDTO updateBlog(Long id, BlogResponceDTO blogResponceDTO) {
        Blog existingBlog = blogRepository.findById(id).orElse(null);
        if (existingBlog == null) {
            return null;
        }

        modelMapper.map(blogResponceDTO, existingBlog);
        Blog savedBlog = blogRepository.save(existingBlog);

        return modelMapper.map(savedBlog, BlogResponceDTO.class);
    }

    @Override
    public boolean deleteBlog(Long id) {
        if(blogRepository.existsById(id)) {
            blogRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

