package com.example.coffo.services;

import com.example.coffo.DTOs.responce.BlogResponceDTO;
import com.example.coffo.models.Blog;
import org.jspecify.annotations.Nullable;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

public interface BlogService {
    BlogResponceDTO getBlogById(Long id);
    BlogResponceDTO createBlog(BlogResponceDTO blogResponceDTO);
    BlogResponceDTO updateBlog(Long id, BlogResponceDTO blogResponceDTO);
    boolean  deleteBlog(Long id);

    List<BlogResponceDTO> getAllBlogs();
}
