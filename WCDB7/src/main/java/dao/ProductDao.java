package dao;

import models.Category;
import models.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class ProductDao {
    private EntityManager em;

    public ProductDao() {
        EntityManagerFactory ef = Persistence.createEntityManagerFactory("B7PU");
        em = ef.createEntityManager();
    }

    public List<Product> getAll(){
        return em.createQuery("SELECT p FROM products p", Product.class).getResultList();
    }

    public void save(Product product) {
        em.getTransaction().begin();
        em.persist(product);
        em.getTransaction().commit();
    }

    public List<Product> getByCateId(Long cateId) {
        return em.createQuery("SELECT p FROM products p WHERE p.category.id = :cateId", Product.class)
                .setParameter("cateId", cateId)
                .getResultList();
    }
}
