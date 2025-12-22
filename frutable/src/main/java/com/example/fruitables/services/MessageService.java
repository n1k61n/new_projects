package com.example.fruitables.services;


import com.example.fruitables.dtos.message.MessageCreateDto;
import com.example.fruitables.dtos.message.MessageDto;
import com.example.fruitables.dtos.message.MessageReadDto;

import java.util.List;

public interface MessageService {
    void createMessage(MessageCreateDto messageCreateDto);

    List<MessageReadDto> getMessages();

    void markAsRead(Long id);

    void sendEmailToAdmin(MessageDto message);


    MessageDto findByIdAndMarkAsRead(Long id);
}
