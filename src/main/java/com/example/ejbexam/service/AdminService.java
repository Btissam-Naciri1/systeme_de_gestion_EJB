package com.example.ejbexam.service;
import com.example.ejbexam.entity.CD;
import jakarta.ejb.Stateful;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;

@Stateful
public class AdminService {
    @PersistenceContext
    private EntityManager entityManager;

    public void addCD(CD cd) {
        cd.setAvailable(true); // Set new CD as available
        entityManager.persist(cd);
    }

    public void updateCD(CD cd) {
        entityManager.merge(cd);
    }

    public void deleteCD(Long cdId) {
        CD cd = entityManager.find(CD.class, cdId);
        if (cd != null) {
            entityManager.remove(cd);
        }
    }

    public List<CD> listAllCDs() {
        TypedQuery<CD> query = entityManager.createQuery("SELECT c FROM CD c", CD.class);
        return query.getResultList();
    }
}
