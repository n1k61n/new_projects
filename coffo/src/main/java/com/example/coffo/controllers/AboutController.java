package com.example.coffo.controllers;

import com.example.coffo.DTOs.responce.AboutResponceDTO;
import com.example.coffo.services.AboutService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
@RequiredArgsConstructor
public class AboutController {
    private final AboutService aboutService;
    private final ModelMapper modelMapper;

    @GetMapping("/about")
    public String about(Model model) {
        AboutResponceDTO aboutResponceDTO = aboutService.getAboutInfo(1l);
        model.addAttribute("aboutInfo", aboutResponceDTO);
        return "about";
    }
}
