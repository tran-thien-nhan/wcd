/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Product;

public class ProductServlet extends HttpServlet {

    private List<Product> products = new ArrayList<>();
    private int productId = 1;

    @Override
    public void init() throws ServletException {
        super.init(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        products.add(new Product(productId++, "banh my", 10.22, 10));
        products.add(new Product(productId++, "banh bo", 22.11, 11));
        products.add(new Product(productId++, "bun dau", 11.12, 12));
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
                case "update":
                    updateProduct(req, resp);
                    break;
                default:
                    listProduct(req, resp);
            }
        }
    }

    private void createProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        double price = Double.parseDouble(req.getParameter("price"));
        int quantity = Integer.parseInt(req.getParameter("quantity"));
        Product product = new Product(productId++, name, price, quantity);
        products.add(product);
        resp.sendRedirect("Product");
    }

    private void updateProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        double price = Double.parseDouble(req.getParameter("price"));
        int quantity = Integer.parseInt(req.getParameter("quantity"));
        Product productToUpdate = findProductById(id);
        if (productToUpdate != null) {
            productToUpdate.setName(name);
            productToUpdate.setPrice(price);
            productToUpdate.setQuantity(quantity);
            resp.sendRedirect("Product");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");
        if (action == null) {
            listProduct(req, resp);
        } else {
            switch (action) {
                case "list":
                    listProduct(req, resp);
                    break;
                case "delete":
                    deleteProduct(req, resp);
                    break;
                case "new":
                    showFormCreate(req, resp);
                    break;
                case "edit":
                    showFormEdit(req, resp);
                    break;
                default:
                    listProduct(req, resp);
            }
        }
    }

    private void listProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("products", products);
        req.getRequestDispatcher("list.jsp").forward(req, resp);
    }

    private void deleteProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Product product = findProductById(id);
        if (product != null) {
            products.remove(product);
        }
        System.out.println("id delete: " + id);
        resp.sendRedirect("Product");
    }

    private void showFormCreate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("create.jsp").forward(req, resp);
    }

    private void showFormEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Product product = findProductById(id);
        if (product != null) {
            req.setAttribute("product", product);
            req.getRequestDispatcher("edit.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("Product");
        }
    }

    private Product findProductById(int id) {
        for (Product item : products) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }
}
