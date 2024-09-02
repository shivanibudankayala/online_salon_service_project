package com.wipro.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.Entity.Customer;
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
