package com.example.fruitables.controllers.dashboard;

import com.example.fruitables.dtos.message.MessageReadDto;
import com.example.fruitables.dtos.auth.UserNameDto;
import com.example.fruitables.services.MessageService;
import com.example.fruitables.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Collections;
import java.util.List;


@ControllerAdvice(basePackages = "com.example.fruitables.controllers.dashboard")
@RequiredArgsConstructor
public class GlobalDashboardControllerAdvice {

    private final UserService userService;
    private final MessageService messageService;

    @ModelAttribute("adminName")
    public UserNameDto addAdminProfileToModel() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        // Əgər istifadəçi login olmayıbsa və ya anonimdirsə bazaya getmə
        if (auth == null || !auth.isAuthenticated() || auth.getPrincipal().equals("anonymousUser")) {
            return new UserNameDto();
        }

        // Bu metodun içində bazaya getmək əvəzinə auth.getName() istifadə edə bilərsiniz
        return userService.getCurrentAdmin();
    }

    @ModelAttribute("messages")
    public List<MessageReadDto> unReadMessages() {
        // Loglarda gördüyümüz sonsuz SELECT-in qarşısını almaq üçün:
        // Əgər həqiqətən admin deyilsə mesajları çəkməyə ehtiyac yoxdur
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            return messageService.getMessages();
        }
        return Collections.emptyList();
    }
}