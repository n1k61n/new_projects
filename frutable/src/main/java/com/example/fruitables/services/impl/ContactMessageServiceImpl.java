package com.example.fruitables.services.impl;

import com.example.fruitables.dtos.contact.ContactMessageCreateDto;
import com.example.fruitables.dtos.contact.ContactMessageDto;
import com.example.fruitables.dtos.contact.ContactMessageReadDto;
import com.example.fruitables.models.ContactMessage;
import com.example.fruitables.repositories.ContactMessageRepository;
import com.example.fruitables.services.ContactMessageService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ContactMessageServiceImpl implements ContactMessageService {


    @Autowired
    private JavaMailSender mailSender;
    private final ModelMapper modelMapper;
    private final ContactMessageRepository contactMessageRepository;

    @Override
    public void createMessage(ContactMessageCreateDto contactMessageCreateDto) {
        ContactMessage contactMessage = modelMapper.map(contactMessageCreateDto, ContactMessage.class);
        contactMessageRepository.save(contactMessage);
    }

    @Override
    public List<ContactMessageReadDto> getContactMessages() {
        List<ContactMessage> contactMessages = contactMessageRepository.findByIsReadFalse();
        return contactMessages
                .stream()
                .map(msg -> modelMapper.map(msg, ContactMessageReadDto.class)) // Burada çeviririk
                .toList();
    }

    @Override
    public void markAsRead(Long id) {
        ContactMessage contactMessage = contactMessageRepository.findById(id).orElseThrow();
        contactMessage.setRead(true);
        contactMessageRepository.save(contactMessage);
    }


    public void sendEmailToAdmin(ContactMessageDto message) {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo("eminelxanoglu@gmail.com");
        mail.setSubject("Yeni Əlaqə Mesajı: " + message.getId());
        mail.setText("Göndərən: " + message.getName() + " (" + message.getEmail() + ")\n\n" +
                "Mesaj: " + message.getMessage());

        mailSender.send(mail);
    }


    @Override
    public ContactMessageDto findByIdAndMarkAsRead(Long id) {
        ContactMessage contactMessage = contactMessageRepository.findById(id).orElse(null);
        if(contactMessage != null){
            contactMessage.setRead(true);
            contactMessageRepository.save(contactMessage);
            return modelMapper.map(contactMessage, ContactMessageDto.class);
        }
        return null;
    }
}