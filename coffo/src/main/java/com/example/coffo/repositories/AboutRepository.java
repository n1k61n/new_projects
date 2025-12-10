package com.example.coffo.repositories;

import com.example.coffo.DTOs.responce.AboutResponceDTO;
import com.example.coffo.models.About;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AboutRepository  extends JpaRepository<About, Long> {

}


