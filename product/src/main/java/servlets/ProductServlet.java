/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import dao.ProductDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Product;

public class ProductServlet extends HttpServlet {

    ProductDao dao;

    @Override
    public void init() throws ServletException {
        dao = new ProductDao();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            listProduct(req, resp);
        } else {
            switch (action) {
                case "create":
                    createProduct(req, resp);
                    break;
                //update product
                case "update":
                    updateProduct(req, resp);
                    break;
                //search by name
                case "search":
                    searchProduct(req, resp);
                    break;
                default:
                    listProduct(req, resp);
            }
        }
    }

    private void updateProduct(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String price = req.getParameter("price");
        String quantity = req.getParameter("quantity");
        Map<String, String> errors = new HashMap();
        if (name == null || name.trim().isEmpty()) {
            errors.put("name", "Name is required");
        } else if (name.length() < 3 || name.length() > 50) {
            errors.put("name", "Name must be between 3 and 50 characters");
        } else if (name.matches(".*\\d.*")) {
            errors.put("name", "Name must not contain number");
        }
        double priceValue = 0;
        if (price != null && !price.trim().isEmpty()) {
            try {
                priceValue = Double.parseDouble(price);
                if (priceValue < 100 || priceValue > 300) {
                    errors.put("price", "Price must be between 100 and 300");
                }
            } catch (NumberFormatException e) {
                errors.put("price", "Price is not a valid number");
            }
        } else {
            errors.put("price", "Price is required");
        }

        int quantityValue = 0;
        if (quantity != null && !quantity.trim().isEmpty()) {
            try {
                quantityValue = Integer.parseInt(quantity);
                if (quantityValue < 10 || quantityValue > 50) {
                    errors.put("quantity", "Quantity must be between 10 and 50");
                }
            } catch (NumberFormatException e) {
                errors.put("quantity", "Quantity is not a valid number");
            }
        } else {
            errors.put("quantity", "Quantity is required");
        }
        if (!errors.isEmpty()) {
            req.setAttribute("errors", errors);
            req.setAttribute("product", new Product(id, name, priceValue, quantityValue));
            try {
                req.getRequestDispatcher("edit.jsp").forward(req, resp);
            } catch (Exception e) {
                System.out.println("Error update in servlet: " + e.getMessage());
            }
            return;
        }
        Product pro = new Product(id, name, priceValue, quantityValue);
        dao.updateProduct(pro);
        try {
            resp.sendRedirect("product");
        } catch (IOException e) {
            System.out.println("Error update in servlet: " + e.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            listProduct(req, resp);
        } else {
            switch (action) {
                case "create":
                    formCreateProduct(req, resp);
                    break;
                case "sortAsc":
                    sortAscPrice(req, resp);
                    break;
                case "sortDesc":
                    sortDescPrice(req, resp);
                    break;
                //delete
                case "delete":
                    deleteOneProduct(req, resp);
                    break;
                //chuyen sang trang edit
                case "edit":
                    formEditProduct(req, resp);
                    break;
                default:
                    listProduct(req, resp);
            }
        }
    }

    private void searchProduct(HttpServletRequest req, HttpServletResponse resp) {
        String name = req.getParameter("name");
        List<Product> list = dao.searchProduct(name);
        req.setAttribute("products", list);
        try {
            req.getRequestDispatcher("list.jsp").forward(req, resp);
        } catch (Exception e) {
            System.out.println("Error search in servlet: " + e.getMessage());
        }
    }

    private void formEditProduct(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("id"));
        Product pro = dao.getProductById(id);
        req.setAttribute("product", pro);
        try {
            req.getRequestDispatcher("edit.jsp").forward(req, resp);
        } catch (Exception e) {
            System.out.println("Error edit in servlet: " + e.getMessage());
        }
    }

    private void deleteOneProduct(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("id"));
        dao.deleteProduct(id);
        try {
            resp.sendRedirect("product");
        } catch (IOException e) {
            System.out.println("Error delete in servlet: " + e.getMessage());
        }
    }

    private void sortDescPrice(HttpServletRequest req, HttpServletResponse resp) {
        try {
            List<Product> list = dao.sortPriceDesc();
            req.setAttribute("products", list);
            req.getRequestDispatcher("list.jsp").forward(req, resp);
        } catch (Exception e) {
            System.out.println("Error get all in servlet: " + e.getMessage());
        }
    }

    private void sortAscPrice(HttpServletRequest req, HttpServletResponse resp) {
        try {
            List<Product> list = dao.sortPriceAsc();
            req.setAttribute("products", list);
            req.getRequestDispatcher("list.jsp").forward(req, resp);
        } catch (Exception e) {
            System.out.println("Error get all in servlet: " + e.getMessage());
        }
    }

    private void listProduct(HttpServletRequest req, HttpServletResponse resp) {
        try {
            List<Product> list = dao.ListAllProduct();
            req.setAttribute("products", list);
            req.getRequestDispatcher("list.jsp").forward(req, resp);
        } catch (Exception e) {
            System.out.println("Error get all in servlet: " + e.getMessage());
        }
    }

    private void formCreateProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("create.jsp").forward(req, resp);
    }

    private void createProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String priceParam = req.getParameter("price");
        String quantityParam = req.getParameter("quantity");
        Map<String, String> errors = new HashMap();
        if (name == null || name.trim().isEmpty()) {
            errors.put("name", "Name is required");
        } else if (name.length() < 3 || name.length() > 50) {
            errors.put("name", "Name must be between 3 and 50 characters");
        } //name khong co chứa số
        else if (name.matches(".*\\d.*")) {
            errors.put("name", "Name must not contain number");
        }
        double price = 0;
        if (priceParam != null && !priceParam.trim().isEmpty()) {
            try {
                price = Double.parseDouble(priceParam);
                if (price < 100 || price > 300) {
                    errors.put("price", "Price must be between 100 and 300");
                }
            } catch (NumberFormatException e) {
                errors.put("price", "Price is not a valid number");
            }
        } else {
            errors.put("price", "Price is required");
        }
        int quantity = 0;
        if (quantityParam != null && !quantityParam.trim().isEmpty()) {
            try {
                quantity = Integer.parseInt(quantityParam);
                if (quantity < 10 || quantity > 50) {
                    errors.put("quantity", "Quantity must be between 10 and 50");
                }
            } catch (NumberFormatException e) {
                errors.put("quantity", "Quantity is not a valid number");
            }
        } else {
            errors.put("quantity", "Quantity is required");
        }
        if (!errors.isEmpty()) {
            req.setAttribute("errors", errors);
            req.setAttribute("name", name);
            req.setAttribute("price", priceParam);
            req.setAttribute("quantity", quantityParam);
            req.getRequestDispatcher("create.jsp").forward(req, resp);
            return;
        }
        Product pro = new Product(name, price, quantity);
        dao.insertProduct(pro);
        resp.sendRedirect("product");
    }
}
