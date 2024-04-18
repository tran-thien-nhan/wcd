package dao;

import models.Brand;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class BrandDao {

    private EntityManager em;

    public BrandDao() {
        EntityManagerFactory ef = Persistence.createEntityManagerFactory("MINIPROJECT");
        em = ef.createEntityManager();
    }

    //getlistbrand
    public List<Brand> getListBrand() {
        List<Brand> list = em.createQuery("SELECT b FROM brands b",Brand.class).getResultList();
        return list;
    }

    //create brand
    public void createBrand(Brand brand) {
        em.getTransaction().begin();
        em.persist(brand);
        em.getTransaction().commit();
    }

    //findBrandById
    public Brand findBrandById(int id) {
        Brand brand = em.find(Brand.class, id);
        return brand;
    }

    //update brand
    public void updateBrand(Brand brand) {
        em.getTransaction().begin();
        em.merge(brand);
        em.getTransaction().commit();
    }
}
