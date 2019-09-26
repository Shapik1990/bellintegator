package ru.bellintegrator.practice.country.dao;

import ru.bellintegrator.practice.country.model.Country;

public interface CountryDao {
    Country loadById(int id);
}
