package com.example.coffo.services;

import com.example.coffo.DTOs.request.AboutRequestDTO;
import com.example.coffo.DTOs.responce.AboutResponceDTO;


public interface AboutService {
    AboutResponceDTO getAboutInfo();
    AboutResponceDTO createAboutInfo(AboutRequestDTO aboutRequestDTO);
    AboutResponceDTO updateAboutInfo(long id, AboutRequestDTO aboutRequestDTO);
    boolean deleteAboutInfo(long id);
}


