package dao;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Product;

public class ProductDao extends HttpServlet {

    Connection cnn;

    public boolean validateProduct(Product product) {
        if (product.getName() == null || product.getName().isEmpty()) {
            return false;
        }
        if (product.getPrice() <= 0) {
            return false;
        }
        if (product.getQuantity() <= 0) {
            return false;
        }
        return true;
    }

    public List<Product> ListAllProduct() {
        List<Product> list = new ArrayList<>();
        cnn = ConnectDB.connect();
        try {
            String sql = "SELECT * FROM product";
            Statement stmt = cnn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                Product emp = new Product(id, name, price, quantity);
                list.add(emp);
            }
            rs.close();
            stmt.close();
            ConnectDB.disconnect();
        } catch (Exception e) {
            System.out.println("Error get list from db: " + e.getMessage());
        }
        return list;
    }

    public void insertProduct(Product product) {
        try {
            cnn = ConnectDB.connect();
            String sql = "INSERT INTO product(name,price,quantity) VALUES(?,?,?)";
            PreparedStatement pstmt = cnn.prepareStatement(sql);
            pstmt.setString(1, product.getName());
            pstmt.setDouble(2, product.getPrice());
            pstmt.setInt(3, product.getQuantity());
            pstmt.executeUpdate();
            cnn.close();
            ConnectDB.disconnect();
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
        }
    }

    //sort price asc
    public List<Product> sortPriceAsc() {
        List<Product> list = new ArrayList<>();
        cnn = ConnectDB.connect();
        try {
            String sql = "SELECT * FROM product ORDER BY price ASC";
            Statement stmt = cnn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                Product emp = new Product(id, name, price, quantity);
                list.add(emp);
            }
            rs.close();
            stmt.close();
            ConnectDB.disconnect();
        } catch (Exception e) {
            System.out.println("Error get list from db: " + e.getMessage());
        }
        return list;
    }

    //sort price desc
    public List<Product> sortPriceDesc() {
        List<Product> list = new ArrayList<>();
        cnn = ConnectDB.connect();
        try {
            String sql = "SELECT * FROM product ORDER BY price DESC";
            Statement stmt = cnn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                Product emp = new Product(id, name, price, quantity);
                list.add(emp);
            }
            rs.close();
            stmt.close();
            ConnectDB.disconnect();
        } catch (Exception e) {
            System.out.println("Error get list from db: " + e.getMessage());
        }
        return list;
    }

    //delete
    public void deleteProduct(int id) {
        try {
            cnn = ConnectDB.connect();
            String sql = "DELETE FROM product WHERE id=?";
            PreparedStatement pstmt = cnn.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            cnn.close();
            ConnectDB.disconnect();
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
        }
    }

    //update product
    public void updateProduct(Product product) {
        try {
            cnn = ConnectDB.connect();
            String sql = "UPDATE product SET name=?, price=?, quantity=? WHERE id=?";
            PreparedStatement pstmt = cnn.prepareStatement(sql);
            pstmt.setString(1, product.getName());
            pstmt.setDouble(2, product.getPrice());
            pstmt.setInt(3, product.getQuantity());
            pstmt.setInt(4, product.getId());
            pstmt.executeUpdate();
            cnn.close();
            ConnectDB.disconnect();
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
        }
    }

    public Product getProductById(int id) {
        Product pro = null;
        cnn = ConnectDB.connect();
        try {
            String sql = "SELECT * FROM product WHERE id=?";
            PreparedStatement pstmt = cnn.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                pro = new Product(id, name, price, quantity);
            }
            rs.close();
            pstmt.close();
            ConnectDB.disconnect();
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
        }
        return pro;
    }

    //tim kiem theo ten
    public List<Product> searchProduct(String name) {
        List<Product> list = new ArrayList<>();
        cnn = ConnectDB.connect();
        try {
            String sql = "SELECT * FROM product WHERE name LIKE ?";
            PreparedStatement pstmt = cnn.prepareStatement(sql);
            pstmt.setString(1, "%" + name + "%");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name1 = rs.getString("name");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                Product emp = new Product(id, name1, price, quantity);
                list.add(emp);
            }
            rs.close();
            pstmt.close();
            ConnectDB.disconnect();
        } catch (Exception e) {
            System.out.println("Error get list from db: " + e.getMessage());
        }
        return list;
    }

    //tim kiem theo id va sap xep desc
    public List<Product> searchProductAndSortDesc(int id) {
        List<Product> list = new ArrayList<>();
        cnn = ConnectDB.connect();
        try {
            String sql = "SELECT * FROM product WHERE id=? ORDER BY price DESC";
            PreparedStatement pstmt = cnn.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int id1 = rs.getInt("id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                Product emp = new Product(id1, name, price, quantity);
                list.add(emp);
            }
            rs.close();
            pstmt.close();
            ConnectDB.disconnect();
        } catch (Exception e) {
            System.out.println("Error get list from db: " + e.getMessage());
        }
        return list;
    }
}
