package com.wipro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.Entity.SalonService;
import com.wipro.repository.ServiceRepository;

@RestController
@RequestMapping("/services")
public class ServiceController {

    @Autowired
    private ServiceRepository serviceRepository;

    @GetMapping
    public List<SalonService> getAllServices() {
        return serviceRepository.findAll();
    }

    @GetMapping("/{id}")
    public SalonService getServiceById(@PathVariable Long id) {
        return serviceRepository.findById(id).orElse(null);
    }

    @PostMapping
    public SalonService addService(@RequestBody SalonService serviceEntity) {
        return serviceRepository.save(serviceEntity);
    }

    @PutMapping("/{id}")
    public SalonService updateService(@PathVariable Long id, @RequestBody SalonService updatedService) {
    	SalonService existingService = serviceRepository.findById(id).orElse(null);
        if (existingService != null) {
            existingService.setName(updatedService.getName());
            existingService.setDescription(updatedService.getDescription());
            existingService.setPrice(updatedService.getPrice());
            return serviceRepository.save(existingService);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteService(@PathVariable Long id) {
        serviceRepository.deleteById(id);
    }
}