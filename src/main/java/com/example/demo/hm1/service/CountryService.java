package com.example.demo.hm1.service;

import com.example.demo.hm1.pojo.University;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CountryService {
    List<University> getAllCountryUniversity();
    List<University> getUniversityByCountry(String country);
}
