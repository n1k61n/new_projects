package com.example.fruitables.controllers.dashboard;

import com.example.fruitables.dtos.message.MessageReadDto;
import com.example.fruitables.dtos.auth.UserNameDto;
import com.example.fruitables.services.MessageService;
import com.example.fruitables.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalDashboardControllerAdvice {

    @Autowired
    private final UserService userService;
    @Autowired
    private final MessageService messageService;

    @ModelAttribute("adminName")
    public UserNameDto addAdminProfileToModel() {
        return userService.getCurrentAdmin();
    }

    @ModelAttribute("messages")
    public List<MessageReadDto> unReadMessages(){
        return messageService.getMessages();
    }

}