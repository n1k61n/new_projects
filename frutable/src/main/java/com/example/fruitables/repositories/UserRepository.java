package com.example.fruitables.repositories;


import com.example.fruitables.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    Optional<User> findByVerificationToken(String token);

    boolean existsByEmail(String email);

    @Query(value = "SELECT u.* FROM users u JOIN roles r ON u.id = r.id WHERE r.name = :roleName LIMIT 1", nativeQuery = true)
    User findByRoleName(@Param("roleName") String roleName);
}
