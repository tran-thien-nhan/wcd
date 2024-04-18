package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import models.Product;

public class ProductDao {

    private EntityManager em;

    public ProductDao() {
        EntityManagerFactory ef = Persistence.createEntityManagerFactory("MINIPROJECT");
        em = ef.createEntityManager();
    }

    //get list products
    public List<Product> getAllProducts() {
        List<Product> list = em.createQuery("SELECT p FROM products p", Product.class).getResultList();
        return list;
    }

    //add product
    public void add(Product product) {
        em.getTransaction().begin();
        em.persist(product);
        em.getTransaction().commit();
    }

    //get product by id
    public Product getById(Long id) {
        return em.find(Product.class, id);
    }

    //update product
    public void update(Product product) {
        em.getTransaction().begin();
        em.merge(product);
        em.getTransaction().commit();
    }

    //view product by category
    public List<Product> getProductsByCategory(Long categoryId) {
        List<Product> list = em.createQuery("SELECT p FROM products p WHERE p.category.id = :categoryId", Product.class)
                .setParameter("categoryId", categoryId)
                .getResultList();
        return list;
    }

    //view product by brand
    public List<Product> getProductsByBrand(Long brandId) {
        List<Product> list = em.createQuery("SELECT p FROM products p WHERE p.brand.id = :brandId", Product.class)
                .setParameter("brandId", brandId)
                .getResultList();
        return list;
    }
}
