package com.example.fruitables.repositories;


import com.example.fruitables.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findByVerificationToken(String token);

    boolean existsByEmail(String email);
}
