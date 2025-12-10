package com.example.agency.services.impl;


import com.example.agency.dtos.responce.ServiceItemResponceDTO;
import com.example.agency.models.ServiceItem;
import com.example.agency.repositories.ServiceItemRepository;
import com.example.agency.services.ServiceItemServive;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

@RequiredArgsConstructor
public class ServiceItemServiceImpl implements ServiceItemServive {
    private final ServiceItemRepository repository;
    private final ModelMapper modelMapper;

    @Override
    public ServiceItemResponceDTO getServiceItemById(Long id) {
        if (repository.existsById(id)) {
            return modelMapper.map(repository.findById(id).orElse(null), ServiceItemResponceDTO.class);
        }
        return null;
    }

    @Override
    public ServiceItemResponceDTO createServiceItem(ServiceItemResponceDTO dto) {
        if (dto == null) return null;
        ServiceItem serviceItem = modelMapper.map(dto, ServiceItem.class);
        ServiceItem serviceSave = repository.save(serviceItem);
        return modelMapper.map(serviceSave, ServiceItemResponceDTO.class);
    }

    @Override
    public ServiceItemResponceDTO updateServiceItem(Long id, ServiceItemResponceDTO dto) {
        ServiceItem serviceItem = repository.findById(id).orElse(null);
        if (serviceItem != null && dto != null) {
            serviceItem.setDescription(dto.getDescription());
            serviceItem.setIcon(dto.getIcon());
            serviceItem.setTitle(dto.getTitle());
            repository.save(serviceItem);
            return dto;
        }
        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
