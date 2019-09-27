package ru.bellintegrator.practice.country.dao;

import ru.bellintegrator.practice.country.model.Country;

import java.util.List;

public interface CountryDao {

    /**
     * Получить объект Country по идентификатору
     *
     * @param id идентификатор объекта Country
     * @return {@link Country}
     */
    Country loadById(int id);

    /**
     * Получить все объекты Country
     *
     * @return список {@link Country}
     */
    List<Country> loadAll();
}
