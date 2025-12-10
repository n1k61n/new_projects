package com.example.agency.services.impl;

import com.example.agency.dtos.contact.ContactCreateDTO;
import com.example.agency.dtos.contact.ContactDTO;
import com.example.agency.dtos.contact.ContactUpdateDTO;
import com.example.agency.models.Contact;
import com.example.agency.repositories.ContactRepository;
import com.example.agency.services.ContactService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {
    private final ContactRepository repository;
    private final ModelMapper modelMapper;


    @Override
    public List<ContactDTO> getAllContacts() {
        List<Contact> contacts = repository.findAll();
        if (!contacts.isEmpty()) {
            return contacts.stream().map(contact -> modelMapper.map(contact, ContactDTO.class)).toList();
        }
        return List.of();
    }

    @Override
    public boolean createContact(ContactCreateDTO contactCreateDTO) {
        try {
            Contact contact = modelMapper.map(contactCreateDTO, Contact.class);
            repository.save(contact);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public ContactUpdateDTO getUpdateContact(Long id) {
        Contact contact = repository.findById(id).orElse(null);
        if (contact != null) {
            return modelMapper.map(contact, ContactUpdateDTO.class);
        }
        return new ContactUpdateDTO();
    }

    @Override
    public boolean updateContact(Long id, ContactUpdateDTO contactUpdateDTO) {
        try {
            Contact contact = modelMapper.map(contactUpdateDTO, Contact.class);
            contact.setId(id);
            repository.save(contact);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean deleteContact(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
