package com.example.hoursmanagement.service;

import com.example.hoursmanagement.model.SpecialHours;
import com.example.hoursmanagement.repository.SpecialHoursRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class SpecialHoursService {

    @Autowired
    private SpecialHoursRepository repository;

    public List<SpecialHours> getAllSpecialHours() {
        return repository.findAll();
    }

    public Optional<SpecialHours> getSpecialHoursByDate(LocalDate date) {
        return repository.findByDate(date);
    }

    public SpecialHours createSpecialHours(SpecialHours specialHours) {
        return repository.save(specialHours);
    }
}
