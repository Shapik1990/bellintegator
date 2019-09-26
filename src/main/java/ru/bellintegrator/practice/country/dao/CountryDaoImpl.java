package ru.bellintegrator.practice.country.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.country.model.Country;

import javax.persistence.EntityManager;

@Repository
public class CountryDaoImpl implements CountryDao {

    private EntityManager em;

    @Autowired
    public CountryDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public Country loadById(int id) {
        return em.find(Country.class, id);
    }
}
