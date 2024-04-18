package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.User;

public class UserDao {
    
    private EntityManagerFactory ef;
    private EntityManager em;

    public UserDao() {
        this.ef = Persistence.createEntityManagerFactory("B5PU");
    }
    
    //find all user có role = "User"
    public List<User> findAllUser() {
        em = ef.createEntityManager();
        List<User> list = em.createQuery("SELECT u FROM usertb u WHERE u.role = 'User'").getResultList();
        em.close();
        return list;
    }
    
    //create new user
    public void saveUser(User user) {
        em = ef.createEntityManager();
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        em.close();
    }
    
    //delete a user
    public void deleteUser(Long id) {
        em = ef.createEntityManager();
        em.getTransaction().begin();
        User user = em.find(User.class, id);
        em.remove(user);
        em.getTransaction().commit();
        em.close();
    }

    //find username and password do exist role = "User"
    public User findUser(String username, String password) {
        em = ef.createEntityManager();
        try {
            User user = (User) em.createQuery("SELECT u FROM usertb u WHERE u.username = :username AND u.password = :password")
                    .setParameter("username", username)
                    .setParameter("password", password)
                    .getSingleResult();
            return user;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<User> findAllAdmin() {
        em = ef.createEntityManager();
        List<User> list = em.createQuery("SELECT u FROM usertb u WHERE u.role = 'Admin'").getResultList();
        em.close();
        return list;
    }
}
