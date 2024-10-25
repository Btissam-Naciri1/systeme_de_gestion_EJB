package com.example.ejbexam.service;
import com.example.ejbexam.entity.CD;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import com.example.ejbexam.entity.CD;
import jakarta.persistence.TypedQuery;

import java.util.List;


@Stateless
public class CDService {
    @PersistenceContext
    private EntityManager entityManager;

    public List<CD> listAvailableCDs() {
        TypedQuery<CD> query = entityManager.createQuery("SELECT c FROM CD c WHERE c.available = true", CD.class);
        return query.getResultList();
    }

    public void borrowCD(Long cdId) {
        CD cd = entityManager.find(CD.class, cdId);
        if (cd != null && cd.isAvailable()) {
            cd.setAvailable(false);
            entityManager.merge(cd);
        }
    }

    public void returnCD(Long cdId) {
        CD cd = entityManager.find(CD.class, cdId);
        if (cd != null) {
            cd.setAvailable(true);
            entityManager.merge(cd);
        }
    }
}
