package com.example.coffo.services.impl;


import com.example.coffo.DTOs.request.AboutRequestDTO;

import com.example.coffo.DTOs.responce.AboutResponceDTO;
import com.example.coffo.models.About;
import com.example.coffo.repositories.AboutRepository;
import com.example.coffo.services.AboutService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;




@Service
@RequiredArgsConstructor
public class AboutServiceImpl implements AboutService {

    private final AboutRepository aboutRepository;
    private final ModelMapper modelMapper;


    @Override
    public AboutResponceDTO getAboutInfo() {
        About about = aboutRepository.findAll().stream().findFirst().orElse(null);
        if(about != null) {
            AboutRequestDTO dto = new AboutRequestDTO();
            dto.setDescription(about.getDescription());
            dto.setImageUrl(about.getImageUrl());
            dto.setMainTitle(about.getMainTitle());
            dto.setSubTitle(about.getSubTitle());
            return modelMapper.map(dto, AboutResponceDTO.class);
        }
        return null;
    }

    @Override
    public AboutResponceDTO createAboutInfo(AboutRequestDTO aboutRequestDTO) {
        About  about = new About();
        about.setDescription(aboutRequestDTO.getDescription());
        about.setImageUrl(aboutRequestDTO.getImageUrl());
        about.setMainTitle(aboutRequestDTO.getMainTitle());
        about.setSubTitle(aboutRequestDTO.getSubTitle());
        About savedAbout = aboutRepository.save(about);
        return modelMapper.map(savedAbout, AboutResponceDTO.class);
    }

    @Override
    public AboutResponceDTO updateAboutInfo(long id, AboutRequestDTO aboutRequestDTO) {
        About existingAbout = aboutRepository.findById(id).orElse(null);
        if (existingAbout == null) {
            return null;
        }
        existingAbout.setDescription(aboutRequestDTO.getDescription());
        existingAbout.setImageUrl(aboutRequestDTO.getImageUrl());
        existingAbout.setMainTitle(aboutRequestDTO.getMainTitle());
        existingAbout.setSubTitle(aboutRequestDTO.getSubTitle());
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