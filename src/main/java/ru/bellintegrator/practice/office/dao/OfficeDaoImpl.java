package ru.bellintegrator.practice.office.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.office.model.Office;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OfficeDaoImpl implements OfficeDao {

    private EntityManager em;

    @Autowired
    public OfficeDaoImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Office loadById(Integer id) {
        return em.find(Office.class, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Office> listByFilter(int orgId, String name, String phone, Boolean active) {
        List<Predicate> predicates = new ArrayList<>();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Office> criteriaQuery = cb.createQuery(Office.class);
        Root<Office> root = criteriaQuery.from(Office.class);
        predicates.add(cb.equal(root.get("orgId").get("id"), orgId));

        if (name != null){
            predicates.add(cb.equal(root.get("name"), name));
        }
        if (phone != null){
            predicates.add(cb.equal(root.get("phone"), phone));
        }
        if (active != null){
            predicates.add(cb.equal(root.get("isActive"), active));
        }

        criteriaQuery.select(root).where(predicates.toArray(new Predicate[]{}));
        return em.createQuery(criteriaQuery).getResultList();
    }
}
