package com.example.fruitables.repositories;

import com.example.fruitables.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommnetRrepository extends JpaRepository<Comment, Long> {
}
