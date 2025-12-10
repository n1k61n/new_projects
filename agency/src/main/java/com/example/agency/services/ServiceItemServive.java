package com.example.agency.services;

import com.example.agency.dtos.responce.ServiceItemResponceDTO;

public interface ServiceItemServive {
    ServiceItemResponceDTO getServiceItemById(Long id);

    ServiceItemResponceDTO createServiceItem(ServiceItemResponceDTO dto);

    ServiceItemResponceDTO updateServiceItem(Long id, ServiceItemResponceDTO dto);

    boolean deleteById(Long id);
}
