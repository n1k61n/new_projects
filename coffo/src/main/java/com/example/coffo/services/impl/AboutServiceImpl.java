package com.example.coffo.services.impl;


import com.example.coffo.DTOs.request.AboutRequestDTO;
import com.example.coffo.DTOs.responce.AboutResponceDTO;
import com.example.coffo.models.About;
import com.example.coffo.repositories.AboutRepository;
import com.example.coffo.services.AboutService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class AboutServiceImpl implements AboutService {

    private final AboutRepository aboutRepository;
    private final ModelMapper modelMapper;


    @Override
    public AboutResponceDTO getAboutInfo() {
        About about = aboutRepository.findAll().stream().findFirst().orElse(null);
        if(about != null) {
            AboutResponceDTO dto = new AboutResponceDTO();
            dto.setDescription(about.getDescription());
            dto.setImageUrl(about.getImageUrl());
            dto.setMainTitle(about.getMainTitle());
            dto.setSubTitle(about.getSubTitle());
            dto.setId(about.getId());
            return dto;
        }
        return null;
    }

    @Override
    public AboutResponceDTO createAboutInfo(AboutResponceDTO aboutResponceDTO) {
        About  about = new About();
        about.setDescription(aboutResponceDTO.getDescription());
        about.setImageUrl(aboutResponceDTO.getImageUrl());
        about.setMainTitle(aboutResponceDTO.getMainTitle());
        about.setSubTitle(aboutResponceDTO.getSubTitle());
        About savedAbout = aboutRepository.save(about);
        return aboutResponceDTO;
    }

    @Override
    public AboutResponceDTO updateAboutInfo(long id, AboutResponceDTO aboutResponceDTO) {
        About existingAbout = aboutRepository.findById(id).orElse(null);
        if (existingAbout == null) {
            return createAboutInfo(aboutResponceDTO);
        }
        existingAbout.setDescription(aboutResponceDTO.getDescription());
        existingAbout.setImageUrl(aboutResponceDTO.getImageUrl());
        existingAbout.setMainTitle(aboutResponceDTO.getMainTitle());
        existingAbout.setSubTitle(aboutResponceDTO.getSubTitle());
        existingAbout.setId(id);
        About savedAbout = aboutRepository.save(existingAbout);
        return modelMapper.map(savedAbout, AboutResponceDTO.class);
    }

    @Override
    public boolean deleteAboutInfo(long id) {
        if (aboutRepository.existsById(id)) {
            aboutRepository.deleteById(id);
            return true;
        }
        return false;

    }
}