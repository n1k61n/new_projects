package com.example.agency.services.impl;

import com.example.agency.dtos.responce.AboutResponceDTO;
import com.example.agency.models.About;
import com.example.agency.repositories.AboutRepository;
import com.example.agency.services.AboutService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AboutServiceImpl implements AboutService {
    private final AboutRepository aboutRepository;
    private final ModelMapper modelMapper;

    @Override
    public AboutResponceDTO getAboutById(Long id) {
        Optional<About> aboutOptional = aboutRepository.findById(id);
        if (aboutOptional.isEmpty()) {
            return null;
        }
        About entity = aboutOptional.get();
        return modelMapper.map(entity, AboutResponceDTO.class);
    }

    @Override
    public AboutResponceDTO createAbout(AboutResponceDTO aboutResponceDTO) {
        About aboutEntity = new About();
        aboutEntity.setTitle(aboutResponceDTO.getTitle());
        aboutEntity.setDate(aboutResponceDTO.getDate());
        aboutEntity.setDescription(aboutResponceDTO.getDescription());
        aboutEntity.setImageUrl(aboutResponceDTO.getImageUrl());
        aboutRepository.save(aboutEntity);
        return modelMapper.map(aboutEntity, AboutResponceDTO.class);
    }

    @Override
    public AboutResponceDTO updateAbout(Long id, AboutResponceDTO aboutResponceDTO) {
        About aboutEntity = aboutRepository.findById(id).orElse(null);
        if (aboutEntity == null) return null;
        aboutEntity.setTitle(aboutResponceDTO.getTitle());
        aboutEntity.setDate(aboutResponceDTO.getDate());
        aboutEntity.setDescription(aboutResponceDTO.getDescription());
        aboutEntity.setImageUrl(aboutResponceDTO.getImageUrl());
        aboutRepository.save(aboutEntity);
        return modelMapper.map(aboutEntity, AboutResponceDTO.class);
    }

    @Override
    public boolean deleteAbout(Long id) {
        if (aboutRepository.existsById(id)) {
            aboutRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
