package servlets;

import dao.CategoryDao;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Category;

public class CategoryServlet extends HttpServlet {

    private CategoryDao cateDao;

    @Override
    public void init() throws ServletException {
        //super.init(); 
        cateDao = new CategoryDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            getListCategory(req, resp);
        } else {
            switch (action) {
                case "create":
                    showFormCreateCategory(req, resp);
                    break;
                case "edit":
                   //editCategory(req, resp);
                    break;
                case "delete":
                    //deleteCategory(req, resp);
                    break;
                default:
                    getListCategory(req, resp);
                    break;
            }
        }
    }

    private void showFormCreateCategory(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("category/create.jsp").forward(req, resp);
    }

    private void getListCategory(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("categories", cateDao.getListCategory());
        req.getRequestDispatcher("category/list.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            getListCategory(req, resp);
        } else {
            switch (action) {
                case "create":
                    createCategory(req, resp);
                    break;
                case "edit":
                   //editCategory(req, resp);
                    break;
                case "delete":
                    //deleteCategory(req, resp);
                    break;
                default:
                    getListCategory(req, resp);
                    break;
            }
        }
    }

    private void createCategory(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        Category category = new Category(name);
        cateDao.createCategory(category);
        //back ve trang danh sach category
        resp.sendRedirect("category");
    }


}
