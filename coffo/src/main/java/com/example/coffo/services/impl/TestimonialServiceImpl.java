package com.example.coffo.services.impl;

import com.example.coffo.DTOs.responce.TestimonialResponceDTO;
import com.example.coffo.repositories.TestimonialRepository;
import com.example.coffo.services.TestimonialService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TestimonialServiceImpl implements TestimonialService {

    private final TestimonialRepository testimonialRepository;
    // XƏTA VERƏN SƏTİR SİLİNDİ: private final TestimonialResponceDTO testimonialResponceDTO;
    private final ModelMapper modelMapper;

    // YENİ DÜZGÜN getAll metodu (Boş List qaytarmaq əvəzinə)
    @Override
    public List<TestimonialResponceDTO> getAll() {
        // 1. Repository-dən bütün Entity-ləri gətiririk
        return testimonialRepository.findAll().stream()
                // 2. Hər bir Entity-ni ModelMapper ilə DTO-ya çeviririk
                .map(entity -> modelMapper.map(entity, TestimonialResponceDTO.class))
                // 3. List olaraq qaytarırıq
                .collect(Collectors.toList());
    }
}