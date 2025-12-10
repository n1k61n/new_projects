package com.example.agency.services;

import com.example.agency.dtos.responce.TeamResponceDTO;

public interface TeamService {
    TeamResponceDTO getTeamById(Long Id);

    TeamResponceDTO createTeam(TeamResponceDTO dto);

    TeamResponceDTO updateTeam(Long id, TeamResponceDTO dto);

    boolean deleteTeam(Long id);

}
