package ru.bellintegrator.practice.country.service;

import ru.bellintegrator.practice.country.dto.CountryDto;

import java.util.List;

public interface CountryService {
    /**
     * Получить список стран из справочной таблицы
     *
     * @return список стран обернутых в {@link CountryDto}
     */
    List<CountryDto> getAllCountries();
}
