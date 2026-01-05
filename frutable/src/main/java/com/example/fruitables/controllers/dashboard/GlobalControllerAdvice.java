package com.example.fruitables.controllers.dashboard;

import com.example.fruitables.dtos.user.UserNameDto;
import com.example.fruitables.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;


@ControllerAdvice(basePackages = "com.example.fruitables.controllers.dashboard")
@RequiredArgsConstructor
public class GlobalControllerAdvice {

    private final UserService userService;

    @ModelAttribute("adminName")
    public UserNameDto addAdminProfileToModel() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated() || auth.getPrincipal().equals("anonymousUser")) {
            return new UserNameDto();
        }
        return userService.getCurrentAdmin();
    }
}
