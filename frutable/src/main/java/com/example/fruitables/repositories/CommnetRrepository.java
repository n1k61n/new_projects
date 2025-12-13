package com.example.fruitables.repositories;

import com.example.fruitables.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommnetRrepository extends JpaRepository<Comment, Long> {
}
