package com.example.agency.repositories;

import com.example.agency.models.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {
    
}
