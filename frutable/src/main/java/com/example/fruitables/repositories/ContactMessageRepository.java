package com.example.fruitables.repositories;

import com.example.fruitables.dtos.contact.ContactMessageReadDto;
import com.example.fruitables.models.ContactMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactMessageRepository extends JpaRepository<ContactMessage, Long> {
    List<ContactMessage> findByIsReadFalse();
}
