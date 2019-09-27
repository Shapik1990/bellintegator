package ru.bellintegrator.practice.country.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.country.dao.CountryDao;
import ru.bellintegrator.practice.country.dto.CountryDto;
import ru.bellintegrator.practice.country.model.Country;
import ru.bellintegrator.practice.exception.NotEntityException;

import java.util.ArrayList;
import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    private CountryDao dao;

    @Autowired
    public CountryServiceImpl(CountryDao dao) {
        this.dao = dao;
    }

    /**
     *{@inheritDoc}
     */
    @Override
    @Transactional
    public List<CountryDto> getAllCountries() {
        List<Country> countryList = dao.loadAll();

        if (countryList.isEmpty()) {
            throw new NotEntityException("Страны в справочной таблице не найдены");
        }

        List<CountryDto> dtoList = new ArrayList<>();

        for (Country country : countryList) {
            dtoList.add(new CountryDto(country));
        }

        return dtoList;
    }
}
