package com.example.agency.services;

import com.example.agency.dtos.contact.ContactCreateDTO;
import com.example.agency.dtos.contact.ContactDTO;
import com.example.agency.dtos.contact.ContactUpdateDTO;

import java.util.List;

public interface ContactService {

    List<ContactDTO> getAllContacts();

    boolean createContact(ContactCreateDTO contactCreateDTO);

    ContactUpdateDTO getUpdateContact(Long id);

    boolean updateContact(Long id, ContactUpdateDTO contactUpdateDTO);

    boolean deleteContact(Long id);
}
