package com.example.fruitables.services.impl;

import com.example.fruitables.dtos.contact.MessageCreateDto;
import com.example.fruitables.dtos.contact.MessageDto;
import com.example.fruitables.dtos.contact.MessageReadDto;
import com.example.fruitables.models.Message;
import com.example.fruitables.repositories.MessageRepository;
import com.example.fruitables.services.MessageService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {


    @Autowired
    private JavaMailSender mailSender;
    private final ModelMapper modelMapper;
    private final MessageRepository messageRepository;

    @Override
    public void createMessage(MessageCreateDto messageCreateDto) {
        Message message = modelMapper.map(messageCreateDto, Message.class);
        messageRepository.save(message);
    }

    @Override
    public List<MessageReadDto> getMessages() {
        List<Message> messages = messageRepository.findByIsReadFalse();
        return messages
                .stream()
                .map(msg -> modelMapper.map(msg, MessageReadDto.class)) // Burada çeviririk
                .toList();
    }

    @Override
    public void markAsRead(Long id) {
        Message message = messageRepository.findById(id).orElseThrow();
        message.setRead(true);
        messageRepository.save(message);
    }


    public void sendEmailToAdmin(MessageDto message) {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo("eminelxanoglu@gmail.com");
        mail.setSubject("Yeni Əlaqə Mesajı: " + message.getId());
        mail.setText("Göndərən: " + message.getName() + " (" + message.getEmail() + ")\n\n" +
                "Mesaj: " + message.getMessage());

        mailSender.send(mail);
    }


    @Override
    public MessageDto findByIdAndMarkAsRead(Long id) {
        Message message = messageRepository.findById(id).orElse(null);
        if(message != null){
            message.setRead(true);
            messageRepository.save(message);
            return modelMapper.map(message, MessageDto.class);
        }
        return null;
    }
}