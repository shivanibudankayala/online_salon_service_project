package com.wipro.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.Entity.SalonService;
import com.wipro.repository.ServiceRepository;

@Service
public class SalonServiceService {

    @Autowired
    private ServiceRepository serviceRepository;

    public List<SalonService> getAllServices() {
        return serviceRepository.findAll();
    }

    public Optional<SalonService> getServiceById(Long id) {
        return serviceRepository.findById(id);
    }

    public SalonService createOrUpdateService(SalonService service) {
        return serviceRepository.save(service);
    }

    public void deleteService(Long id) {
        serviceRepository.deleteById(id);
    }
}
