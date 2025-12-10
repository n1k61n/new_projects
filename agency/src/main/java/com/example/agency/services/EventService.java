package com.example.agency.services;

import com.example.agency.dtos.responce.EventResponceDTO;

public interface EventService {
    EventResponceDTO getEventById(Long id);

    EventResponceDTO createEvent(EventResponceDTO dto);

    EventResponceDTO updateEvent(Long id, EventResponceDTO dto);

    boolean deleteById(Long id);
}
