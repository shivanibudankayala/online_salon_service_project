package com.wipro.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.entity.Staff;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {

}
