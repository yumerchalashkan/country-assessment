package com.example.country_assessment.service;

import com.example.country_assessment.converter.AsiaMostBorderDtoConverter;
import com.example.country_assessment.converter.CountrySortedDtoConverter;
import com.example.country_assessment.dto.AsiaMostBorderDto;
import com.example.country_assessment.dto.CountrySortedDto;
import com.example.country_assessment.model.Country;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CountryService {

    @Value("${api.url}")
    private String API;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final AsiaMostBorderDtoConverter asiaMostBorderDtoConverter;
    private final CountrySortedDtoConverter countrySortedDtoConverter;

    public CountryService(RestTemplate restTemplate, ObjectMapper objectMapper, AsiaMostBorderDtoConverter asiaMostBorderDtoConverter, CountrySortedDtoConverter countrySortedDtoConverter) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
        this.asiaMostBorderDtoConverter = asiaMostBorderDtoConverter;
        this.countrySortedDtoConverter = countrySortedDtoConverter;
    }

    public AsiaMostBorderDto getAsiaCountryMostBorders() throws JsonProcessingException {
        List<Country> allCountries = getAllCountriesFromApi();

        //stored as a map
        Map<String, Country> countryMap = new HashMap<>();
        for (Country country : allCountries) {
            countryMap.put(country.getCca3(), country);
        }

        //number of borders
        Country mostBorders = null;
        int max = 0;

        //find the country with the most borders
        for (Country country : allCountries) {
            if ("Asia".equals(country.getRegion())) {
                int borderCount = countBordersWithDifferentRegions(country, countryMap);
                if (borderCount > max) {
                    max = borderCount;
                    mostBorders = country;
                }
            }
        }

        return asiaMostBorderDtoConverter.convert(mostBorders);

    }


    public List<CountrySortedDto> getCountriesSorted() throws JsonProcessingException {
        //fetching all countries from api
        List<Country> countries = getAllCountriesFromApi();

        //it performs the sorting process according to population density with the help of the comparator class
        countries.sort(new Comparator<Country>() {
            @Override
            public int compare(Country countryFirst, Country countrySecond) {
                //compares and sorts
                double densityFirstCompare = countryFirst.getPopulationDensity();
                double densitySecondCompare = countrySecond.getPopulationDensity();
                return Double.compare(densitySecondCompare, densityFirstCompare);
            }
        });

        //returns sorted countries as CountrySortedDto
        return countries.stream()
                .sorted(Comparator.comparingDouble(Country::getPopulationDensity).reversed())
                .map(x -> countrySortedDtoConverter.convert(x))
                .collect(Collectors.toList());
    }


    //gets request from api via rest template
    private List<Country> getAllCountriesFromApi() throws JsonProcessingException {
            String response = restTemplate.getForObject(API, String.class);
            return objectMapper.readValue(response, new TypeReference<List<Country>>() {});
    }

    private int countBordersWithDifferentRegions(Country country, Map<String, Country> countryMap) {

        //checking whether the country has a border
        if (country.getBorders() == null) {
            return 0;
        }

        //where the counting is done with the lambda expression
        return (int) Arrays.stream(country.getBorders())
                .map(countryMap::get)
                .filter(Objects::nonNull)
                .filter(x -> !x.getRegion().equals(country.getRegion()))
                .count();
    }


}
