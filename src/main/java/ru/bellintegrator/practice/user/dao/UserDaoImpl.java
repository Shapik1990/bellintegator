package ru.bellintegrator.practice.user.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.user.model.User;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    private EntityManager em;

    @Autowired
    public UserDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public User loadById(int id) {
        return em.find(User.class, id);
    }

    @Override
    public List<User> listByFilter(Integer officeId, String firstName, String secondName, String middleName, String position, Integer docCode, Integer citizenshipCode) {
        List<Predicate> predicates = new ArrayList<>();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = cb.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);
        predicates.add(cb.equal(root.get("officeId").get("id"), officeId));

        if (firstName != null) {
            predicates.add(cb.equal(root.get("firstName"), firstName));
        }
        if (secondName != null) {
            predicates.add(cb.equal(root.get("secondName"), secondName));
        }
        if (middleName != null){
            predicates.add(cb.equal(root.get("middleName"), middleName));
        }
        if (position != null) {
            predicates.add(cb.equal(root.get("position"), position));
        }
        if (docCode != null) {
            predicates.add(cb.equal(root.get("docCode"), docCode));
        }
        if (citizenshipCode != null){
            predicates.add((cb.equal(root.get("citizenshipCode"), citizenshipCode)));
        }

        criteriaQuery.select(root).where(predicates.toArray(new Predicate[]{}));
        return em.createQuery(criteriaQuery).getResultList();
    }
}
