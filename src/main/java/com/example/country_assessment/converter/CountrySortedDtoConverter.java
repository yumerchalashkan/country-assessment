package com.example.country_assessment.converter;

import com.example.country_assessment.dto.CountrySortedDto;
import com.example.country_assessment.model.Country;
import org.springframework.stereotype.Component;

@Component
public class CountrySortedDtoConverter {
    public CountrySortedDto convert(Country from){
        return new CountrySortedDto(from.getName(), from.getPopulationDensity(), from.getPopulation(), from.getArea());

    }
}
