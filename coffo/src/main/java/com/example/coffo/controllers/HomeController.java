package com.example.coffo.controllers;


import com.example.coffo.DTOs.responce.AboutResponceDTO;
import com.example.coffo.DTOs.responce.TestimonialResponceDTO;

import com.example.coffo.services.TestimonialService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

import static org.apache.logging.log4j.util.LambdaUtil.getAll;


@Controller
@RequiredArgsConstructor
public class HomeController {

    private final ModelMapper modelMapper;
    private final TestimonialService testimonialService;

    @GetMapping("/index")
    public String home(Model model) {
        List<TestimonialResponceDTO> testimonialResponceDTOS =  testimonialService.getAll();
        model.addAttribute("testimonials", testimonialResponceDTOS);
        return "index";
    }





}


