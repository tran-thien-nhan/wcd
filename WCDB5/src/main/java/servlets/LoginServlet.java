package servlets;

import dao.UserDao;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;

public class LoginServlet extends HttpServlet {

    UserDao dao;

    @Override
    public void init() throws ServletException {
        super.init(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        dao = new UserDao();
    }   
    
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if(action.equals("logout")) {
            HttpSession session = req.getSession();
            if (session != null) {
                session.invalidate();
            }
            resp.sendRedirect("login.jsp");
        }
        else{
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User user = dao.findUser(username, password);
        if (user != null) {
            req.getSession().setAttribute("user", user);
            if (user.getRole().equals("User")) {
                resp.sendRedirect("user");
            } else {
                resp.sendRedirect("user?action=listAdmin");
            }
        } else {
            req.setAttribute("invalid", "Username or password is incorrect");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    } 
    
}
