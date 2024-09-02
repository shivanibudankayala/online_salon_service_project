package com.wipro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.Entity.SalonService;
@Repository
public interface ServiceRepository extends JpaRepository<SalonService, Long> {

}
