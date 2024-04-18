package dao;

import models.Category;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class CategoryDao {

    private EntityManager em;

    public CategoryDao() {
        EntityManagerFactory ef = Persistence.createEntityManagerFactory("MINIPROJECT");
        em = ef.createEntityManager();
    }

    //getListCategory
    public List<Category> getListCategory() {
        List<Category> list = em.createQuery("SELECT c FROM categories c",Category.class).getResultList();
        return list;
    }

    //create category
    public void createCategory(Category category) {
        em.getTransaction().begin();
        em.persist(category);
        em.getTransaction().commit();
    }

    //findCategoryById
    public Category findCategoryById(int id) {
        Category category = em.find(Category.class, id);
        return category;
    }

    //update category
    public void updateCategory(Category category) {
        em.getTransaction().begin();
        em.merge(category);
        em.getTransaction().commit();
    }
}
