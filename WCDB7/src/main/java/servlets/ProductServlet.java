package servlets;

import dao.CategoryDao;
import dao.ProductDao;
import models.Category;
import models.Product;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProductServlet extends HttpServlet {

    private ProductDao productDao;
    private CategoryDao cateDao;

    @Override
    public void init() throws ServletException {
        //super.init(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        productDao = new ProductDao();
        cateDao = new CategoryDao();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            getListProduct(req, resp);
        } else {
            switch (action) {
                case "create":
                    createProduct(req, resp);
                    break;
                case "edit":
                    //editProduct(req, resp);
                    break;
                case "delete":
                    //deleteProduct(req, resp);
                    break;
                default:
                    getListProduct(req, resp);
                    break;
            }
        }
    }

    private void viewProductByCate(HttpServletRequest req, HttpServletResponse resp) {
        Long cateId = Long.valueOf(req.getParameter("id")); //
        List<Product> products = productDao.getByCateId(cateId);
        req.setAttribute("products", products);
        try {
            req.getRequestDispatcher("product/list.jsp").forward(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void createProduct(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        double price = Double.parseDouble(req.getParameter("price"));
        Long categoryId = Long.valueOf(req.getParameter("categoryId"));
        Product product = new Product(name, price, categoryId);
        productDao.save(product);
        //back ve trang danh sach product
        resp.sendRedirect("product");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            getListProduct(req, resp);
        } else {
            switch (action) {
                case "create":
                    showFormCreateProduct(req, resp);
                    break;
                case "edit":
                    //editProduct(req, resp);
                    break;
                case "delete":
                    //deleteProduct(req, resp);
                    break;
                //view
                case "view":
                    viewProductByCate(req, resp);
                    break;
                default:
                    getListProduct(req, resp);
                    break;
            }
        }
    }

    private void showFormCreateProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Category> cates = cateDao.getAll();
        req.setAttribute("categories", cates);
        req.getRequestDispatcher("product/create.jsp").forward(req, resp);
    }

    private void getListProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> products = productDao.getAll();
        req.setAttribute("products", products);
        req.getRequestDispatcher("product/list.jsp").forward(req, resp);
    }
    
    

}
