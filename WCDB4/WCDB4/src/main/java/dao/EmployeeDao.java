package dao;

import java.util.List;
import javax.persistence.*;
import models.Employee;

public class EmployeeDao {
    //EntityManagerFactory la mot interface trong goi
    //javax.persistence ->su dung de tao ra cac  EntityManager
    //No cung cap mot cach de dang de tuong tac voi persistence unit

    private EntityManagerFactory ef;
    private EntityManager em;

    public EmployeeDao() {
        this.ef = Persistence.createEntityManagerFactory("B4PU");
    }

    public List<Employee> findAll() {
        //Khoi tao 1 entityManager tu EntityManagerFactory
        em = ef.createEntityManager();
        //Thuc hien truy van JPQL de lay tat ca nhan vien tu ban emptb
        List<Employee> employees = em.createQuery("SELECT e FROM emptb e", Employee.class).getResultList();
        //Dong em de giai phong tai nguyen khi khong con su dung                                                                         
        em.close();
        return employees;
    }

    public void saveEmployee(Employee emp) {
        //Khoi tao 1 entityManager tu EntityManagerFactory
        em = ef.createEntityManager();
        //bat dau mot transaction 
        em.getTransaction().begin();
        //Luu mot doi tuong va CSDL
        em.persist(emp);
        //commit de apply cac thay doi vao CSDL
        em.getTransaction().commit();
        em.close();
    }

    public Employee findById(Long id) {
        em = ef.createEntityManager();
        Employee emp = em.find(Employee.class, id);
        return emp;
    }

    public void delete(Long id) {
        //khoi tao mot entityManager
        em = ef.createEntityManager();
        //tim employee theo id
        Employee emp = findById(id);
        // Bat dau mot giao dich
        em.getTransaction().begin();
        em.remove(em.contains(emp) ? emp : em.merge(emp));
        //commit de apply su thay doi
        em.getTransaction().commit();
        em.close();
    }
}
