/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import dao.employeeDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Employee;

public class EmployeeServlet extends HttpServlet {

    employeeDao dao;

    @Override
    public void init() throws ServletException {
        dao = new employeeDao();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            listEmployee(req, resp);
        } else {
            switch (action) {
                case "create":
                    createEmployee(req, resp);
                    break;
                default:
                    listEmployee(req, resp);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            listEmployee(req, resp);
        } else {
            switch (action) {
                case "create":
                    formCreateEmployee(req, resp);
                    break;
                case "delete":
                    deleteEmployee(req, resp);
                    break;
                case "sort":
                    String sort = req.getParameter("sort");
                    if (sort.equals("age_asc")) {
                        sortAgeASC(req, resp);
                    } else if (sort.equals("age_desc")) {
                        sortAgeDESC(req, resp);
                    }
                    break;
                    //fine one employee
                case "find":
                    findEmployee(req, resp);
                    break;
                default:
                    listEmployee(req, resp);
            }
        }
    }

    private void findEmployee(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("id"));
        Employee emp = dao.findEmployee(id);
        req.setAttribute("employee", emp);
        try {
            req.getRequestDispatcher("find.jsp").forward(req, resp);
        } catch (Exception e) {
            System.out.println("Error find in servlet: " + e.getMessage());
        }
    }

    private void sortAgeDESC(HttpServletRequest req, HttpServletResponse resp) {
        try {
            List<Employee> list = dao.sortEmployeeByAgeDESC();
            req.setAttribute("employees", list);
            req.getRequestDispatcher("list.jsp").forward(req, resp);
        } catch (Exception e) {
            System.out.println("Error get all in servlet: " + e.getMessage());
        }
    }

    private void sortAgeASC(HttpServletRequest req, HttpServletResponse resp) {
        try {
            List<Employee> list = dao.sortEmployeeByAgeASC();
            req.setAttribute("employees", list);
            req.getRequestDispatcher("list.jsp").forward(req, resp);
        } catch (Exception e) {
            System.out.println("Error get all in servlet: " + e.getMessage());
        }
    }


    private void deleteEmployee(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("id"));
        dao.deleteEmployee(id);
        try {
            resp.sendRedirect("employee");
        } catch (IOException e) {
            System.out.println("Error delete in servlet: " + e.getMessage());
        }
    }

    private void listEmployee(HttpServletRequest req, HttpServletResponse resp) {
        try {
            List<Employee> list = dao.ListAllEmployee();
            req.setAttribute("employees", list);
            req.getRequestDispatcher("list.jsp").forward(req, resp);
        } catch (Exception e) {
            System.out.println("Error get all in servlet: " + e.getMessage());
        }
    }

    private void formCreateEmployee(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("create.jsp").forward(req, resp);
    }

    private void createEmployee(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        int age = Integer.parseInt(req.getParameter("age"));
        String email = req.getParameter("email");
        Employee emp = new Employee(name, age, email);
        dao.insertUser(emp);
        resp.sendRedirect("employee");
    }
}
