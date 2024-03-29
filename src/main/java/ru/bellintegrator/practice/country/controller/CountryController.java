package ru.bellintegrator.practice.country.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.practice.country.dto.CountryDto;
import ru.bellintegrator.practice.country.service.CountryService;

import java.util.List;

@RestController
@RequestMapping(value = "/countries")
public class CountryController {

    private CountryService service;

    @Autowired
    public CountryController(CountryService service) {
        this.service = service;
    }

    @GetMapping
    public List<CountryDto> getAllCounties(){
        return service.getAllCountries();
    }
}
