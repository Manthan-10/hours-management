package com.example.hoursmanagement.repository;

import com.example.hoursmanagement.model.CustomerServiceHours;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.DayOfWeek;
import java.util.List;

public interface CustomerServiceHoursRepository extends JpaRepository<CustomerServiceHours, Long> {
	
    List<CustomerServiceHours> findByDayOfWeek(DayOfWeek dayOfWeek);
    
}
