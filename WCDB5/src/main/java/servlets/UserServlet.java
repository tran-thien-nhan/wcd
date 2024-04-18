package servlets;

import dao.UserDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User;

public class UserServlet extends HttpServlet {

    UserDao dao;

    @Override
    public void init() throws ServletException {
        super.init(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        dao = new UserDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            getAllUser(req, resp);
        } else {
            switch (action) {
                case "delete":
                    deleteUser(req, resp);
                    break;
                case "create":
                    showFormCreateUser(req, resp);
                    break;
                    //list admin
                case "listAdmin":
                    getListAdmin(req, resp);
                    break;
                default:
                    getAllUser(req, resp);
            }
        }
    }

    private void getListAdmin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> list = dao.findAllAdmin();
        req.setAttribute("admins", list);
        req.getRequestDispatcher("listAdmin.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            getAllUser(req, resp);
        } else {
            switch (action) {
                case "create":
                    CreateUser(req, resp);
                    break;
                default:
                    getAllUser(req, resp);
            }
        }
    }

    //tạo hàm validate chung
    public String validate(String username, String password, String role, String age) {
        //Map<String, String> errors = new HashMap();
        //check empty all
        if (username.isEmpty() || password.isEmpty() || role.isEmpty() || age.isEmpty()) {
            return "All fields are required!";
        }
        //check username
        else if (username.length() < 6 || username.length() > 20) {
            return "Username must be between 6 and 20 characters!";
        }
        //check password
        else if (password.length() < 6 || password.length() > 20) {
            return "Password must be between 6 and 20 characters!";
        }
        //check age
        else{
            try {
                int ageInt = Integer.parseInt(age);
                if (ageInt < 18 || ageInt > 100) {
                    return "Age must be between 18 and 100!";
                }
            } catch (NumberFormatException e) {
                return "Age must be a number!";
            }
        }

        return null;
    }

    private void getAllUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> list = dao.findAllUser();
        req.setAttribute("users", list);
        req.getRequestDispatcher("list.jsp").forward(req, resp);
    }

    private void deleteUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        dao.deleteUser(id);
        resp.sendRedirect("user");
    }

    private void showFormCreateUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("create.jsp").forward(req, resp);
    }

    private void CreateUser(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String role = req.getParameter("role");
        String age = req.getParameter("age");
        Map<String, String> errors = new HashMap();
        String error = validate(username, password, role, age);
        if (error != null) {
            errors.put("error", error);
            req.setAttribute("errors", errors);
            req.getRequestDispatcher("create.jsp").forward(req, resp);
        } else {
            int ageInt = Integer.parseInt(age);
            User user = new User(username, role, password, ageInt);
            dao.saveUser(user);
            resp.sendRedirect("user");
        }
    }

}
