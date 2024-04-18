package dao;

import models.Todo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class TodoDao {

    private EntityManagerFactory ef;

    public TodoDao() {
        this.ef = Persistence.createEntityManagerFactory("TESTWCD");
    }
    
    //get list todo list
    public List<Todo> getTodoList() {
        EntityManager em = ef.createEntityManager();
        List<Todo> todoList = em.createQuery("SELECT t FROM todo t",Todo.class).getResultList();
        em.close();
        return todoList;
    }

    //find by id
    public Todo findById(Long id) {
        EntityManager em = ef.createEntityManager();
        Todo todo = em.find(Todo.class, id);
        em.close();
        return todo;
    }

    //delete by id
    public void delete(Long id) {
        EntityManager em = ef.createEntityManager();
        em.getTransaction().begin();
        Todo todo = em.find(Todo.class, id);
        em.remove(todo);
        em.getTransaction().commit();
        em.close();
    }

    //login using account in tomcat (tomcat-users.xml
    public boolean login(String username, String password) {
        return username.equals("admin") && password.equals("admin");
    }


}
