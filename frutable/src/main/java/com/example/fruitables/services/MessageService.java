package com.example.fruitables.services;


import com.example.fruitables.dtos.contact.MessageCreateDto;
import com.example.fruitables.dtos.contact.MessageDto;
import com.example.fruitables.dtos.contact.MessageReadDto;

import java.util.List;

public interface MessageService {
    void createMessage(MessageCreateDto messageCreateDto);

    List<MessageReadDto> getMessages();

    void markAsRead(Long id);

    void sendEmailToAdmin(MessageDto message);


    MessageDto findByIdAndMarkAsRead(Long id);
}
