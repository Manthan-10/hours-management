package com.example.hoursmanagement.controller;

import com.example.hoursmanagement.model.CustomerServiceHours;
import com.example.hoursmanagement.model.SpecialHours;
import com.example.hoursmanagement.service.CustomerServiceHoursService;
import com.example.hoursmanagement.service.SpecialHoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/hours")
public class HoursController {

    @Autowired
    private CustomerServiceHoursService customerServiceHoursService;

    @Autowired
    private SpecialHoursService specialHoursService;

    @GetMapping("/current")
    public List<CustomerServiceHours> getCurrentHours() {
        return customerServiceHoursService.getRegularHours();
    }

    @GetMapping("/special")
    public List<SpecialHours> getAllSpecialHours() {
        return specialHoursService.getAllSpecialHours();
    }

    @GetMapping("/special/{date}")
    public Optional<SpecialHours> getSpecialHoursByDate(@PathVariable("date") String date) {
        return specialHoursService.getSpecialHoursByDate(LocalDate.parse(date));
    }

    @PostMapping("/special")
    public SpecialHours createSpecialHours(@RequestBody SpecialHours specialHours) {
        return specialHoursService.createSpecialHours(specialHours);
    }
}
