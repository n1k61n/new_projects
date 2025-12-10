package com.example.coffo.repositories;

import com.example.coffo.models.Testimonial;
import com.example.coffo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User, Long> {
}
