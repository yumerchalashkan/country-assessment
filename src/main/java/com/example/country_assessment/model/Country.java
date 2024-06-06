package com.example.country_assessment.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Country {
    private String name;
    private String region;
    private String[] borders;
    private int population;
    private double area;
    private String cca3;


    public double getPopulationDensity() {
        return population / area;
    }

    @JsonProperty("name")
    public void setName(Name name) {
        this.name = name.common;
    }

    static class Name {
        public String common;
    }
}