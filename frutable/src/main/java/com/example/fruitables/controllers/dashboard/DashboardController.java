package com.example.fruitables.controllers.dashboard;


import com.example.fruitables.dtos.contact.ContactMessageDto;
import com.example.fruitables.dtos.contact.ContactMessageReadDto;
import com.example.fruitables.models.ContactMessage;
import com.example.fruitables.services.ContactMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequiredArgsConstructor
@RequestMapping("/dashboard")
public class DashboardController {


    private final ContactMessageService contactMessageService;



    @GetMapping
    public String index(Model model){
        List<ContactMessageReadDto> unReadMessages = contactMessageService.getContactMessages();
        model.addAttribute("messages", unReadMessages);
        return "dashboard/index";
    }


    @GetMapping("/messages/read-and-redirect/{id}")
    public String readAndRedirect(@PathVariable Long id) {
        // 1. Mesajı tapırıq və oxundu edirik
//        ContactMessage message = contactMessageService.findById(id);
//        contactMessageService.markAsRead(id);
//
        ContactMessageDto contactMessageDto = contactMessageService.findByIdAndMarkAsRead(id);
        // 2. Gmail-də birbaşa admin@gmail.com-a yazmaq üçün link hazırlayırıq
        // Bu link birbaşa Gmail-in "Yaz" (Compose) pəncərəsini açacaq
        String gmailLink = "https://mail.google.com/mail/?view=cm&fs=1&to=" + contactMessageDto.getEmail() +
                "&su=Cavab: Tecili";

        return "redirect:" + gmailLink;
    }



    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }


}


