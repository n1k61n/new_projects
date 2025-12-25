package com.example.fruitables.controllers.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class MessageController {


    private final MessageService messageService;

    @PostMapping("/contact")
    public String contact(@ModelAttribute MessageCreateDto messageCreateDto) {
        messageCreateDto.setCreatedAt(LocalDateTime.now());
        messageCreateDto.setMessaageRead(false);
        messageService.create(messageCreateDto);
        return "redirect:/contact?success";
    }

}
