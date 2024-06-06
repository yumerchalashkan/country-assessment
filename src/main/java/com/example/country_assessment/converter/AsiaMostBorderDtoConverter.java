package com.example.country_assessment.converter;

import com.example.country_assessment.dto.AsiaMostBorderDto;
import com.example.country_assessment.model.Country;
import org.springframework.stereotype.Component;

@Component
public class AsiaMostBorderDtoConverter {
    public AsiaMostBorderDto convert(Country from){
        return new AsiaMostBorderDto(from.getName(), from.getBorders().length, from.getBorders());

    }
}
