package com.example.fruitables.services.impl;

import com.example.fruitables.dtos.contact.ContactMessageCreateDto;
import com.example.fruitables.models.ContactMessage;
import com.example.fruitables.repositories.ContactMessageRepository;
import com.example.fruitables.services.ContactMessageService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ContactMessageServiceImpl implements ContactMessageService {

    private final ModelMapper modelMapper;
    private final ContactMessageRepository contactMessageRepository;

    @Override
    public void createMessage(ContactMessageCreateDto contactMessageCreateDto) {
        ContactMessage contactMessage = modelMapper.map(contactMessageCreateDto, ContactMessage.class);
        contactMessageRepository.save(contactMessage);
    }
}
