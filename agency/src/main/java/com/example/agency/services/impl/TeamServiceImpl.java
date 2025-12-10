package com.example.agency.services.impl;

import com.example.agency.dtos.responce.TeamResponceDTO;
import com.example.agency.models.Team;
import com.example.agency.repositories.TeamRepository;
import com.example.agency.services.TeamService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {
    private final TeamRepository repository;
    private final ModelMapper modelMapper;

    @Override
    public TeamResponceDTO getTeamById(Long id) {
        if (repository.existsById(id)) {
            return modelMapper.map(repository.findById(id).orElse(null), TeamResponceDTO.class);
        }
        return null;
    }

    @Override
    public TeamResponceDTO createTeam(TeamResponceDTO dto) {
        if (dto == null) return null;
        Team teamMember = modelMapper.map(dto, Team.class);
        Team teamSave = repository.save(teamMember);
        return modelMapper.map(teamSave, TeamResponceDTO.class);
    }

    @Override
    public TeamResponceDTO updateTeam(Long id, TeamResponceDTO dto) {
        Team teamMember = repository.findById(id).orElse(null);
        if (dto == null || teamMember == null) return null;
        teamMember.setFacebookUrl(dto.getFacebookUrl());
        teamMember.setImageUrl(dto.getImageUrl());
        teamMember.setLinkedinUrl(dto.getLinkedinUrl());
        teamMember.setName(dto.getName());
        teamMember.setRole(dto.getRole());
        teamMember.setTwitterUrl(dto.getTwitterUrl());
        repository.save(teamMember);
        return dto;
    }

    @Override
    public boolean deleteTeam(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
