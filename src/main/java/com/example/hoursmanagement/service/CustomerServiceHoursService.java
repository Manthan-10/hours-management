package com.example.hoursmanagement.service;

import com.example.hoursmanagement.model.CustomerServiceHours;
import com.example.hoursmanagement.repository.CustomerServiceHoursRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.List;

@Service
public class CustomerServiceHoursService {

    @Autowired
    private CustomerServiceHoursRepository repository;

    public List<CustomerServiceHours> getRegularHours() {
        return repository.findAll();
    }

    public List<CustomerServiceHours> getHoursByDayOfWeek(DayOfWeek dayOfWeek) {
        return repository.findByDayOfWeek(dayOfWeek);
    }
}
