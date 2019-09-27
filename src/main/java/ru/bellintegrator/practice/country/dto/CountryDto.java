package ru.bellintegrator.practice.country.dto;

import ru.bellintegrator.practice.country.model.Country;

public class CountryDto {

    private String name;
    private int code;

    public CountryDto(Country country) {
        this.name = country.getName();
        this.code = country.getCode();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
