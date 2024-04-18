package dao;

import models.Category;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class CategoryDao {
    private EntityManager em;

    public CategoryDao() {
        EntityManagerFactory ef = Persistence.createEntityManagerFactory("B7PU");
        em = ef.createEntityManager();
    }

    public List<Category> getAll() {
        return em.createQuery("SELECT c FROM categories c", Category.class).getResultList();
    }

    public void save(Category category) {
        em.getTransaction().begin();
        em.persist(category);
        em.getTransaction().commit();
    }
}
