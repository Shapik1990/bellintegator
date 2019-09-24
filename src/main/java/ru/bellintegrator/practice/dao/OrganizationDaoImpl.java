package ru.bellintegrator.practice.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.model.Organization;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public void save(Organization organization) {
        em.persist(organization);
    }

    @Override
    public List<Organization> listByFilter(String name, String inn, Boolean active) {
        List<Predicate> predicates = new ArrayList<>();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Organization> criteriaQuery = cb.createQuery(Organization.class);
        Root<Organization> root = criteriaQuery.from(Organization.class);
        predicates.add(cb.equal(root.get("name"), name));

        if (inn != null){
            predicates.add(cb.equal(root.get("inn"), inn));
        }
        if (active != null){
            predicates.add(cb.equal(root.get("isActive"), active));
        }

        criteriaQuery.select(root).where(predicates.toArray(new Predicate[]{}));
        return em.createQuery(criteriaQuery).getResultList();
    }

}
