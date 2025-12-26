package com.example.fruitables.services.impl;

import com.example.fruitables.dtos.contact.ContactDto;
import com.example.fruitables.models.Contact;
import com.example.fruitables.repositories.ContactRepository;
import com.example.fruitables.services.ContactService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {

    private final ModelMapper modelMapper;
    private final ContactRepository contactRepository;
    @Override
    public boolean createContact(ContactDto contactDto) {
        try {
            Contact contact = modelMapper.map(contactDto, Contact.class);
            contact.setCreatedAt(LocalDateTime.now());
            contactRepository.save(contact);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
