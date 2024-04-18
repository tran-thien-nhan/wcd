package servlets;

import dao.ProductDao;
import models.Product;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProductServlet extends HttpServlet {
    
    ProductDao dao;

    @Override
    public void init() throws ServletException {
        //super.init(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        dao = new ProductDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            getListProduct(req, resp);
        } else{
            switch (action) {
                case "create":
                    showFormCreateProduct(req, resp);
                    break;
                case "delete":
                    deleteProduct(req, resp);
                    break;
                case "changeStatus":
                    changeStatus(req, resp);
                    break;
                default:
                    getListProduct(req, resp);
                    break;
            }
        }
    }

    private void searchPriceRange(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String fromPriceStr = req.getParameter("fromPrice");
        String toPriceStr = req.getParameter("toPrice");

        // Kiểm tra nếu các tham số là rỗng
        if (fromPriceStr.isEmpty() || toPriceStr.isEmpty()) {
            req.setAttribute("error", "Please enter both from and to price to search");
        } else {
            try {
                double fromPrice = Double.parseDouble(fromPriceStr);
                double toPrice = Double.parseDouble(toPriceStr);

                if (fromPrice > toPrice) {
                    req.setAttribute("error", "From price must be less than to price");
                }

                if (fromPrice < 0 || toPrice < 0) {
                    req.setAttribute("error", "Price must be greater than or equal to 0");
                }

                req.setAttribute("products", dao.searchByPrice(fromPrice, toPrice));
            } catch (NumberFormatException e) {
                // Xử lý ngoại lệ nếu người dùng nhập không phải là số
                req.setAttribute("error", "Invalid price format. Please enter valid numbers.");
            }
        }

        try {
            req.getRequestDispatcher("list.jsp").forward(req, resp);
        } catch (ServletException | IOException ex) {
            ex.printStackTrace();
        }
    }


    private void deleteProduct(HttpServletRequest req, HttpServletResponse resp) {
        Long id = Long.parseLong(req.getParameter("id"));
        dao.delete(id);
        try {
            resp.sendRedirect("product");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            getListProduct(req, resp);
        } else{
            switch (action) {
                case "create":
                    createProduct(req, resp);
                    break;
                //searchprice
                case "search":
                    searchPriceRange(req, resp);
                    break;
                default:
                    getListProduct(req, resp);
                    break;
            }
        }
    }

    private void changeStatus(HttpServletRequest req, HttpServletResponse resp) {
        Long id = Long.parseLong(req.getParameter("id"));
        dao.changeStatus(id);
        try {
            resp.sendRedirect("product");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void createProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        double price = Double.parseDouble(req.getParameter("price"));
        String status = req.getParameter("status");
        String cate = req.getParameter("cate");
        //price >0 va <1000$
        if (price < 0 || price > 1000) {
            req.setAttribute("errorPrice", "Price must be greater than or equal to 0 and less than 1000");
            try {
                req.getRequestDispatcher("create.jsp").forward(req, resp);
            } catch (ServletException | IOException ex) {
                ex.printStackTrace();
            }
            return;
        }
        dao.save(new Product(name, price, Boolean.parseBoolean(status), cate));
        req.setAttribute("message", "Create product success");
        req.getRequestDispatcher("create.jsp").forward(req, resp);
    }


    private void showFormCreateProduct(HttpServletRequest req, HttpServletResponse resp) {
        try {
            req.getRequestDispatcher("create.jsp").forward(req, resp);
        } catch (ServletException | IOException ex) {
            ex.printStackTrace();
        }
    }

    private void getListProduct(HttpServletRequest req, HttpServletResponse resp) {
        try {
            req.setAttribute("products", dao.getAll());
            req.getRequestDispatcher("list.jsp").forward(req, resp);
        } catch (ServletException | IOException ex) {
            ex.printStackTrace();
        }
    }


}
