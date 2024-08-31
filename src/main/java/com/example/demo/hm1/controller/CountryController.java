package com.example.demo.hm1.controller;

import com.example.demo.hm1.pojo.University;
import com.example.demo.hm1.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public@RequestMapping(value = "/university")
class CountryController {
    private final CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping()
    public ResponseEntity<University[]> getAllCountryUniversity() {
        return new ResponseEntity<>(countryService.getAllCountryUniversity(), HttpStatus.OK);
    }

    @GetMapping("/{country}")
    public ResponseEntity<List<University>> getUniversityByCountry(@PathVariable("country") String country) {
        return new ResponseEntity<>(countryService.getUniversityByCountry(country), HttpStatus.OK);
    }
}
