package com.example.country_assessment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
public class CountrySortedDto {
    public String name;
    public double populationDensity;
    public int population;
    public double area;
}
