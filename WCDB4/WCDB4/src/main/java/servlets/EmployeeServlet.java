package servlets;

import dao.EmployeeDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Employee;

public class EmployeeServlet extends HttpServlet {

    EmployeeDao dao;

    @Override
    public void init() throws ServletException {
        super.init(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        dao = new EmployeeDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            getAllEmployee(req, resp);
        } else {
            switch (action) {
                case "create":
                    showFormCreateEmployee(req, resp);
                    break;
                case "delete":
                    deleteEmployee(req, resp);
                    break;
                case "edit":
                    getFormEditEmployee(req, resp);
                    break;
                default:
                    getAllEmployee(req, resp);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            getAllEmployee(req, resp);
        } else {
            switch (action) {
                case "create":
                    createEmployee(req, resp);
                    break;
                default:
                    getAllEmployee(req, resp);
            }
        }
    }

    private void getAllEmployee(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Employee> list = dao.findAll();
        req.setAttribute("employees", list);
        req.getRequestDispatcher("list.jsp").forward(req, resp);
    }

    private void showFormCreateEmployee(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("create.jsp").forward(req, resp);
    }

    private void createEmployee(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        int age = Integer.parseInt(req.getParameter("age"));
        double salary = Double.parseDouble(req.getParameter("salary"));
        Employee emp = new Employee(name, age, salary);
        dao.saveEmployee(emp);
        resp.sendRedirect("employee");
    }

    private void deleteEmployee(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        dao.delete(id);
        resp.sendRedirect("employee");
    }

    private void getFormEditEmployee(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        Employee emp = dao.findById(id);
        req.setAttribute("employee", emp);
        req.getRequestDispatcher("edit.jsp").forward(req, resp);
    }

}
