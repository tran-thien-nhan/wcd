package servlets;

import dao.TodoDao;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TodoServlet extends HttpServlet {

    private TodoDao dao;

    @Override
    public void init() throws ServletException {
        dao = new TodoDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            getListToDoList(req, resp);
            //show form login
            //showFormLogin(req, resp);
        } else {
            switch (action) {
                case "list":
                    getListToDoList(req, resp);
                    break;
                case "delete":
                    deleteTask(req, resp);
                    break;
                default:
                    getListToDoList(req, resp);
                    break;
            }
        }
    }

    private void showFormLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            getListToDoList(req, resp);
        } else {
            switch (action) {
                case "delete":
                    deleteTask(req, resp);
                    break;
                    //login
                case "login":
                    loginUser(req, resp);
                    break;
                default:
                    getListToDoList(req, resp);
                    break;
            }
        }
    }

    private void loginUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (dao.login(username, password)) {
            req.getRequestDispatcher("success.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("failed.jsp").forward(req, resp);
        }
    }

    private void deleteTask(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Long id = Long.parseLong(req.getParameter("id"));
        dao.delete(id);
        if (dao.findById(id) == null) {
            req.getRequestDispatcher("success.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("failed.jsp").forward(req, resp);
        }
    }

    private void getListToDoList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("todo", dao.getTodoList());
        req.getRequestDispatcher("list.jsp").forward(req, resp);
    }
}
