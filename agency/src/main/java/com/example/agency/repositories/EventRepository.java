package com.example.agency.repositories;

import com.example.agency.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EventRepository extends JpaRepository<Event, Long> {
    
}
