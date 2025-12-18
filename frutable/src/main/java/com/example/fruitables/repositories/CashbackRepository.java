package com.example.fruitables.repositories;

import com.example.fruitables.models.Cashback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CashbackRepository extends JpaRepository<Cashback, Long> {
}
