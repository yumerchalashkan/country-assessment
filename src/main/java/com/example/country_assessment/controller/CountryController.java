package com.example.country_assessment.controller;

import com.example.country_assessment.dto.AsiaMostBorderDto;
import com.example.country_assessment.dto.CountrySortedDto;
import com.example.country_assessment.model.Country;
import com.example.country_assessment.service.CountryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CountryController {
    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/country/sorted")
    public ResponseEntity<List<CountrySortedDto>> getCountriesSortedByDensity() throws JsonProcessingException {
        return ResponseEntity.ok(countryService.getCountriesSorted());
    }

    @GetMapping("/country/borders")
    public ResponseEntity<AsiaMostBorderDto> getAsiaCountryWithMostBorders() throws JsonProcessingException {
        return ResponseEntity.ok(countryService.getAsiaCountryMostBorders());
    }
}
