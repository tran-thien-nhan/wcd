package servlets;

import dao.BrandDao;
import models.Brand;
import models.Category;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BrandServlet extends HttpServlet {

    private BrandDao bDao;

    @Override
    public void init() throws ServletException {
        //super.init(); 
        bDao = new BrandDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            getListBrand(req, resp);
        } else {
            switch (action) {
                case "create":
                    showFormCreateBrand(req, resp);
                    break;
                case "edit":
                    //editBrand(req, resp);
                    break;
                case "delete":
                    //deleteBrand(req, resp);
                    break;
                default:
                    getListBrand(req, resp);
                    break;
            }
        }
    }

    private void showFormCreateBrand(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("brand/create.jsp").forward(req, resp);
    }

    private void getListBrand(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("brands", bDao.getListBrand());
        req.getRequestDispatcher("brand/list.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            getListBrand(req, resp);
        } else {
            switch (action) {
                case "create":
                    createBrand(req, resp);
                    break;
                case "edit":
                    //editBrand(req, resp);
                    break;
                case "delete":
                    //deleteBrand(req, resp);
                    break;
                default:
                    getListBrand(req, resp);
                    break;
            }
        }
    }

    private void createBrand(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        Brand brand = new Brand(name);
        bDao.createBrand(brand);
        //back ve trang danh sach category
        resp.sendRedirect("brand");
    }


}
