package de.likeherotozero;

import de.likeherotozero.Co2Data;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.io.Serializable;

public class Co2DataService implements Serializable {
    @PersistenceContext(unitName = "nutzerPersistenceUnit")
    private EntityManager entityManager;

    @Transactional
    public void save(Co2Data co2Data) {
        entityManager.persist(co2Data);
    }
    // Daten für Dropdown
    public List<String> getAllCountries() {
        TypedQuery<String> query = entityManager.createQuery(
                "SELECT DISTINCT c.land FROM Co2Data c ORDER BY c.land", String.class);
        return query.getResultList();
    }


}

