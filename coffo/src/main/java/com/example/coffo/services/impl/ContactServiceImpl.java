package com.example.coffo.services.impl;


import com.example.coffo.DTOs.responce.ContactResponceDTO;
import com.example.coffo.models.Contact;
import com.example.coffo.repositories.ContactRepository;
import com.example.coffo.services.ContactService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;
    private final ModelMapper modelMapper;

    @Override
    public ContactResponceDTO getContactById(Long id) {
        Contact contact = contactRepository.findById(id).orElse(null);
        if(contact == null) {
            return null;
        }
        return modelMapper.map(contact, ContactResponceDTO.class);
    }

    @Override
    public ContactResponceDTO createContact(ContactResponceDTO contactResponceDTO) {
        Contact contact = modelMapper.map(contactResponceDTO, Contact.class);
        Contact savedContact = contactRepository.save(contact);
        return modelMapper.map(savedContact, ContactResponceDTO.class);
    }

    @Override
    public ContactResponceDTO updateContact(Long id, ContactResponceDTO contactResponceDTO) {
        Contact existingContact = contactRepository.findById(id).orElse(null);
        if (existingContact == null) {
            return createContact(contactResponceDTO);
        }
        existingContact.setName(contactResponceDTO.getName());
        existingContact.setEmail(contactResponceDTO.getEmail());
        existingContact.setMessage(contactResponceDTO.getMessage());
        existingContact.setPhone(contactResponceDTO.getPhone());
        existingContact.setId(id);
        Contact savedContact = contactRepository.save(existingContact);
        return modelMapper.map(savedContact, ContactResponceDTO.class);
    }

    @Override
    public boolean deleteContact(Long id) {
        if(contactRepository.existsById(id)) {
            contactRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
