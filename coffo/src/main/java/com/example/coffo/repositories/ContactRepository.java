package com.example.coffo.repositories;

import com.example.coffo.models.Blog;
import com.example.coffo.models.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository  extends JpaRepository<Contact, Long> {
}
