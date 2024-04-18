package dao;

import model.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class ProductDao {
    private EntityManagerFactory ef;

    public ProductDao() {
        this.ef = Persistence.createEntityManagerFactory("B6PU");
    }

    public List<Product> findAll() {
        EntityManager em = ef.createEntityManager();
        List<Product> products = em.createQuery("SELECT p FROM products p", Product.class).getResultList();
        em.close();
        return products;
    }

    //create new product with image
    public void create(Product product) {
        EntityManager em = ef.createEntityManager();
        em.getTransaction().begin();
        em.persist(product);
        em.getTransaction().commit();
        em.close();
    }

    //delete product
    public void delete(Long id) {
        EntityManager em = ef.createEntityManager();
        em.getTransaction().begin();
        Product product = em.find(Product.class, id);
        em.remove(product);
        em.getTransaction().commit();
        em.close();
    }

    //find product by id
    public Product findById(Long id) {
        EntityManager em = ef.createEntityManager();
        Product product = em.find(Product.class, id);
        em.close();
        return product;
    }

    //update product
    public void update(Product product) {
        EntityManager em = ef.createEntityManager();
        em.getTransaction().begin();
        em.merge(product);
        em.getTransaction().commit();
        em.close();
    }
}
