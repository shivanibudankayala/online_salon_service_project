package com.wipro.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.Entity.Booking;


@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

}
