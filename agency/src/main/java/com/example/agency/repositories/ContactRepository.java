package com.example.agency.repositories;

import com.example.agency.models.Contact;
import org.springframework.data.jpa.repository.JpaRepository;



public interface ContactRepository extends JpaRepository<Contact, Long>   {
    
}
