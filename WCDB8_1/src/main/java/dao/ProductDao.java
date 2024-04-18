package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import models.Product;
public class ProductDao {
    private EntityManager em;

    public ProductDao() {
        EntityManagerFactory ef = Persistence.createEntityManagerFactory("B8PU");
        em = ef.createEntityManager();
    }

    //getall
    public List<Product> getAll() {
        return em.createQuery("SELECT p FROM products p", Product.class).getResultList();
    }

    //create
    public void save(Product product) {
        em.getTransaction().begin();
        em.persist(product);
        em.getTransaction().commit();
    }

    //findbyid
    public Product findById(Long id) {
        return em.find(Product.class, id);
    }

    //delete
    public void delete(Long id) {
        Product product = findById(id);
        em.getTransaction().begin();
        em.remove(product);
        em.getTransaction().commit();
    }

    //thay doi status
    public void changeStatus(Long id) {
        Product product = findById(id);
        em.getTransaction().begin();
        product.setStatus(!product.isStatus());
        em.getTransaction().commit();
    }

    //search fromprice -> toprice
    public List<Product> searchByPrice(double fromPrice, double toPrice) {
        return em.createQuery("SELECT p FROM products p WHERE p.price BETWEEN :fromPrice AND :toPrice", Product.class)
                .setParameter("fromPrice", fromPrice)
                .setParameter("toPrice", toPrice)
                .getResultList();
    }


}
