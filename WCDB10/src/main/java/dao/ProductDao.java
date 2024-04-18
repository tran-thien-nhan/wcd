package dao;

import models.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class ProductDao {
    private EntityManagerFactory ef;

    public ProductDao() {
        this.ef = Persistence.createEntityManagerFactory("B10PU");
    }

    //getlistproducts
    public List<Product> getListProducts() {
        EntityManager em = ef.createEntityManager();
        List<Product> list = em.createQuery("SELECT p FROM products p", Product.class).getResultList();
        return list;
    }

    //create product
    public void createProduct(Product product) {
        EntityManager em = ef.createEntityManager();
        em.getTransaction().begin();
        em.persist(product);
        em.getTransaction().commit();
    }

    //find product by id type long
    public Product findProductById(long id) {
        EntityManager em = ef.createEntityManager();
        Product product = em.find(Product.class, id);
        return product;
    }

    //delete product by id type long
    public void deleteProduct(long id) {
        EntityManager em = ef.createEntityManager();
        em.getTransaction().begin();
        Product product = em.find(Product.class, id);
        em.remove(product);
        em.getTransaction().commit();
    }

    //update product
    public void updateProduct(Product product) {
        EntityManager em = ef.createEntityManager();
        em.getTransaction().begin();
        em.merge(product);
        em.getTransaction().commit();
        em.close();
    }

    //update status
    public void changeStatus(long id) {
        EntityManager em = ef.createEntityManager();
        em.getTransaction().begin();
        Product product = em.find(Product.class, id);
        product.setStatus(!product.isStatus());
        em.getTransaction().commit();
        em.close();
    }

    //search by name
    public List<Product> searchByName(String name) {
        EntityManager em = ef.createEntityManager();
        List<Product> list = em.createQuery("SELECT p FROM products p WHERE p.name LIKE :name", Product.class)
                .setParameter("name", "%" + name + "%")
                .getResultList();
        return list;
    }

    //sort product theo price asc
    public List<Product> sortProductByPriceAsc() {
        EntityManager em = ef.createEntityManager();
        List<Product> list = em.createQuery("SELECT p FROM products p ORDER BY p.price ASC", Product.class)
                .getResultList();
        return list;
    }

    //sort product theo price desc
    public List<Product> sortProductByPriceDesc() {
        EntityManager em = ef.createEntityManager();
        List<Product> list = em.createQuery("SELECT p FROM products p ORDER BY p.price DESC", Product.class)
                .getResultList();
        return list;
    }

    //filter theo status
    public List<Product> filterByStatus(boolean status) {
        EntityManager em = ef.createEntityManager();
        List<Product> list = em.createQuery("SELECT p FROM products p WHERE p.status = :status", Product.class)
                .setParameter("status", status)
                .getResultList();
        return list;
    }
    
}
