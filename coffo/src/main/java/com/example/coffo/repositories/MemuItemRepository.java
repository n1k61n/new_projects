package com.example.coffo.repositories;

import com.example.coffo.models.Contact;
import com.example.coffo.models.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemuItemRepository  extends JpaRepository<MenuItem, Long> {
}


