package com.example.hoursmanagement.repository;

import com.example.hoursmanagement.model.SpecialHours;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface SpecialHoursRepository extends JpaRepository<SpecialHours, Long> {
	
    Optional<SpecialHours> findByDate(LocalDate date);
    
}
