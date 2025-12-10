package com.example.coffo.repositories;


import com.example.coffo.models.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository  extends JpaRepository<Blog, Long> {

}
