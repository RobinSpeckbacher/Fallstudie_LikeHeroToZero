package de.likeherotozero;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import java.time.LocalDateTime;

@Named
@RequestScoped
public class UserRepository {

    @PersistenceContext
    private EntityManager entityManager;


    // mit JPA eine SQL Abfrage erstellt --> im User die Tabelle definiert. u ist btw der alias für user
    public User findByUsername(String username) {
        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class);
        query.setParameter("username", username);

        return query.getResultStream().findFirst().orElse(null);
    }

    @Transactional
    public void saveUser(User user) {

        entityManager.persist(user);
    }
}
