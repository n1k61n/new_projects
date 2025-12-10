package com.example.agency.services;


import com.example.agency.dtos.responce.AboutResponceDTO;

public interface AboutService {
    AboutResponceDTO getAboutById(Long id);

    AboutResponceDTO createAbout(AboutResponceDTO aboutResponceDTO);

    AboutResponceDTO updateAbout(Long id, AboutResponceDTO aboutResponceDTO);

    boolean deleteAbout(Long id);

}
