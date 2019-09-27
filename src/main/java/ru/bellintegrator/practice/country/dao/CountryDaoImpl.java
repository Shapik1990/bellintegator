package ru.bellintegrator.practice.country.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.country.model.Country;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class CountryDaoImpl implements CountryDao {

    private EntityManager em;

    @Autowired
    public CountryDaoImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Country loadById(int id) {
        return em.find(Country.class, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Country> loadAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Country> criteriaQuery = cb.createQuery(Country.class);
        Root<Country> root = criteriaQuery.from(Country.class);
        criteriaQuery.select(root);
        return em.createQuery(criteriaQuery).getResultList();
    }
}
