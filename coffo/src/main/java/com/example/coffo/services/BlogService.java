package com.example.coffo.services;

import com.example.coffo.DTOs.responce.BlogResponceDTO;


import java.util.List;

public interface BlogService {
    BlogResponceDTO getBlogById(Long id);
    BlogResponceDTO createBlog(BlogResponceDTO blogResponceDTO);
    BlogResponceDTO updateBlog(Long id, BlogResponceDTO blogResponceDTO);
    boolean  deleteBlog(Long id);

    List<BlogResponceDTO> getAllBlogs();
}
