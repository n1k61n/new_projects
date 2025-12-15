package com.example.coffo.services.impl;


import com.example.coffo.DTOs.request.ContactRequestDTO;
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
    public ContactResponceDTO saveContactMessage(ContactRequestDTO contactRequestDTO) {
        Contact contactDto = modelMapper.map(contactRequestDTO, Contact.class);
        Contact saveContact = contactRepository.save(contactDto);
        return modelMapper.map(saveContact, ContactResponceDTO.class);
    }


}
