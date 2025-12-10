package com.example.agency.services;

import com.example.agency.dtos.responce.PortfolioResponceDTO;

public interface PortfolioService {
    PortfolioResponceDTO getPortfolioById(Long id);

    PortfolioResponceDTO createPortfolio(PortfolioResponceDTO dto);

    PortfolioResponceDTO upatePortfolio(Long id, PortfolioResponceDTO dto);

    boolean deletePortfolio(Long id);

}
