package com.example.fruitables.repositories;


import com.example.fruitables.models.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String username);
}
