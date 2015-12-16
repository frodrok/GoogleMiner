package se.joafre.model.repo;

import se.joafre.model.model.Search;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public final class SearchRepository {

    private EntityManagerFactory emf;
    private EntityManager em;

    public SearchRepository() {
        this.emf = Persistence.createEntityManagerFactory("PersistenceUnit");
    }

    public void persist(Search search) {
        em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(search);
        em.getTransaction().commit();
        em.close();
    }

    public Search findById(Long id) {
        em = emf.createEntityManager();
        String query = "SELECT s from Search s JOIN FETCH s.results WHERE s.id = " + id;
        Search search = em.createQuery(query, Search.class).getSingleResult();
        em.close();
        return search;
    }
}
