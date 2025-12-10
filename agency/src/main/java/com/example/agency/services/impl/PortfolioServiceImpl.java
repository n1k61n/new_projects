package com.example.agency.services.impl;


import com.example.agency.dtos.responce.PortfolioResponceDTO;
import com.example.agency.models.Portfolio;
import com.example.agency.repositories.PortfolioRepository;
import com.example.agency.services.PortfolioService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;


@RequiredArgsConstructor
public class PortfolioServiceImpl implements PortfolioService {


    private final PortfolioRepository repository;
    private final ModelMapper modelMapper;

    @Override
    public PortfolioResponceDTO getPortfolioById(Long id) {
        Portfolio item = repository.findById(id).get();
        return modelMapper.map(item, PortfolioResponceDTO.class);
    }


    @Override
    public PortfolioResponceDTO createPortfolio(PortfolioResponceDTO dto) {
        if (dto == null) return null;
        Portfolio portfolioItem = new Portfolio();
        portfolioItem.setCategory(dto.getCategory());
        portfolioItem.setDescription(dto.getDescription());
        portfolioItem.setImageUrl(dto.getImageUrl());
        portfolioItem.setTitle(dto.getTitle());
        repository.save(portfolioItem);
        return dto;
    }

    @Override
    public PortfolioResponceDTO upatePortfolio(Long id, PortfolioResponceDTO dto) {
        Portfolio portfolioItem = repository.findById(id).orElse(null);
        if (dto == null || portfolioItem == null) return null;
        portfolioItem.setCategory(dto.getCategory());
        portfolioItem.setDescription(dto.getDescription());
        portfolioItem.setImageUrl(dto.getImageUrl());
        portfolioItem.setTitle(dto.getTitle());
        repository.save(portfolioItem);
        return dto;
    }


    @Override
    public boolean deletePortfolio(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}