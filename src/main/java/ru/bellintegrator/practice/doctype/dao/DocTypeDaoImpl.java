package ru.bellintegrator.practice.doctype.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.doctype.model.DocType;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository
public class DocTypeDaoImpl implements DocTypeDao {

    private EntityManager em;

    @Autowired
    public DocTypeDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public DocType loadById(int id) {
        return em.find(DocType.class, id);
    }

    @Override
    public DocType loadByName(String docName) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<DocType> criteriaQuery = cb.createQuery(DocType.class);
        Root<DocType> root = criteriaQuery.from(DocType.class);
        criteriaQuery.select(root).where(cb.equal(root.get("name"), docName));
        return em.createQuery(criteriaQuery).getSingleResult();
    }
}
