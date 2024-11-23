package de.likeherotozero;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class EmissionsDataRepository {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("nutzerPersistenceUnit");

    public List<EmissionsData> findAll() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<EmissionsData> query = em.createQuery("SELECT e FROM EmissionsData e", EmissionsData.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public EmissionsData findByCountry(String country) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<EmissionsData> query = em.createQuery("SELECT e FROM EmissionsData e WHERE e.country = :country", EmissionsData.class);
            query.setParameter("country", country);
            return query.getSingleResult();
        } finally {
            em.close();
        }
    }
}
