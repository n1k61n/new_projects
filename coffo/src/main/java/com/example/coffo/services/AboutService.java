package com.example.coffo.services;

import com.example.coffo.DTOs.responce.AboutResponceDTO;

public interface AboutService {
    AboutResponceDTO getAboutInfo();
    AboutResponceDTO createAboutInfo(AboutResponceDTO aboutResponceDTO);
    AboutResponceDTO updateAboutInfo(long id, AboutResponceDTO aboutResponceDTO);
    boolean deleteAboutInfo(long id);
}


