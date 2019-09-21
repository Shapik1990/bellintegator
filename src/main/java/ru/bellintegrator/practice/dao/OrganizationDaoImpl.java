package ru.bellintegrator.practice.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.model.Organization;

import javax.persistence.EntityManager;

@Repository
public class OrganizationDaoImpl implements OrganizationDao {

    private EntityManager em;

    @Autowired
    public OrganizationDaoImpl(EntityManager em){
        this.em = em;
    }

    @Override
    public Organization loadById(Integer id) {
        return em.find(Organization.class, id);
    }

    @Override
    public void update(Organization organization) {
        em.merge(organization);
    }


}
