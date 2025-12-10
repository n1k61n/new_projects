package com.example.coffo.services.impl;


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
    public AboutResponceDTO getAboutInfo(long id) {
        Optional<About> aboutOptional = aboutRepository.findById(id);
        if (aboutOptional.isPresent()) {
            About aboutEntity = aboutOptional.get();
            return modelMapper.map(aboutEntity, AboutResponceDTO.class);
        }
        return null;


    }

    @Override
    public AboutResponceDTO createAboutInfo(AboutResponceDTO aboutResponceDTO) {
        About  about = new About();
        about.setDescription(aboutResponceDTO.getDescription());
        about.setImageUrl(aboutResponceDTO.getImageUrl());
        about.setMainTitle(about.getMainTitle());
        about.setSubTitle(about.getSubTitle());
        About savedAbout = aboutRepository.save(about);
        return modelMapper.map(savedAbout, AboutResponceDTO.class);
    }

    @Override
    public AboutResponceDTO updateAboutInfo(long id, AboutResponceDTO aboutResponceDTO) {
        About  about = modelMapper.map(aboutResponceDTO, About.class);
        about.setId(id);
        About savedAbout = aboutRepository.save(about);
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