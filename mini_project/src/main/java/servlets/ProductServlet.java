package servlets;

import dao.BrandDao;
import dao.CategoryDao;
import dao.ProductDao;
import models.Product;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProductServlet extends HttpServlet {

    private ProductDao productDao;
    private CategoryDao categoryDao;
    private BrandDao brandDao;

    @Override
    public void init() throws ServletException {
        //super.init(); 
        productDao = new ProductDao();
        categoryDao = new CategoryDao();
        brandDao = new BrandDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            getListProducts(req, resp);
        } else {
            switch (action) {
                case "create":
                    showFormCreateProduct(req, resp);
                    break;
                    //view product by category
                case "viewByCategory":
                    viewProductByCategory(req, resp);
                    break;
                case "viewByBrand":
                    viewProductByBrand(req, resp);
                    break;
                case "edit":
                    //editProduct(req, resp);
                    break;
                default:
                    getListProducts(req, resp);
                    break;
            }
        }
    }

    private void viewProductByBrand(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long brandId = Long.valueOf(req.getParameter("id"));
        req.setAttribute("products", productDao.getProductsByBrand(brandId));
        req.getRequestDispatcher("product/list.jsp").forward(req, resp);
    }

    private void viewProductByCategory(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long categoryId = Long.valueOf(req.getParameter("id"));
        req.setAttribute("products", productDao.getProductsByCategory(categoryId));
        req.getRequestDispatcher("product/list.jsp").forward(req, resp);
    }


    private void showFormCreateProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("categories", categoryDao.getListCategory());
        req.setAttribute("brands", brandDao.getListBrand());
        req.getRequestDispatcher("product/create.jsp").forward(req, resp);
    }

    private void getListProducts(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("products", productDao.getAllProducts());
        req.getRequestDispatcher("product/list.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            getListProducts(req, resp);
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
                    getListProducts(req, resp);
                    break;
            }
        }
    }

    private void createProduct(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //get data from form
        String name = req.getParameter("name");
        Double price = Double.valueOf(req.getParameter("price"));
        Long categoryId = Long.valueOf(req.getParameter("categoryId"));
        Long brandId = Long.valueOf(req.getParameter("brandId"));
        Product product = new Product(name, price, categoryId, brandId);
        //create product
        productDao.add(product);
        resp.sendRedirect("product");
    }
    
    
}
