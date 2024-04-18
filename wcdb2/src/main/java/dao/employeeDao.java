package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import models.Employee;

public class employeeDao {

    Connection cnn;

    public List<Employee> ListAllEmployee() {
        List<Employee> list = new ArrayList<>();
        cnn = ConnectDB.connect();
        try {
            String sql = "SELECT * FROM emptb";
            Statement stmt = cnn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String email = rs.getString("email");
                Employee emp = new Employee(id, name, age, email);
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

    public void insertUser(Employee employee) {
        try {
            cnn = ConnectDB.connect();
            String sql = "INSERT INTO emptb(name,age,email) VALUES(?,?,?)";
            PreparedStatement pstmt = cnn.prepareStatement(sql);
            pstmt.setString(1, employee.getName());
            pstmt.setInt(2, employee.getAge());
            pstmt.setString(3, employee.getEmail());
            pstmt.executeUpdate();
            cnn.close();
            ConnectDB.disconnect();
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
        }
    }

    public boolean deleteEmployee(int id) {
        boolean rowDeleted = false;
        try {
            cnn = ConnectDB.connect();
            String sql = "DELETE FROM emptb WHERE id = ?";
            PreparedStatement pstmt = cnn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rowDeleted = pstmt.executeUpdate() > 0;
            pstmt.close();
            ConnectDB.disconnect();
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
        }
        return rowDeleted;
    }

    //sort employee by age
    public List<Employee> sortEmployeeByAgeDESC() {
        List<Employee> list = new ArrayList<>();
        cnn = ConnectDB.connect();
        try {
            String sql = "SELECT * FROM emptb ORDER BY age DESC";
            Statement stmt = cnn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String email = rs.getString("email");
                Employee emp = new Employee(id, name, age, email);
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

    //sort age asc
    public List<Employee> sortEmployeeByAgeASC() {
        List<Employee> list = new ArrayList<>();
        cnn = ConnectDB.connect();
        try {
            String sql = "SELECT * FROM emptb ORDER BY age ASC";
            Statement stmt = cnn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String email = rs.getString("email");
                Employee emp = new Employee(id, name, age, email);
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

    //find one employee
    public Employee findEmployee(int id) {
        Employee emp = null;
        cnn = ConnectDB.connect();
        try {
            String sql = "SELECT * FROM emptb WHERE id = ?";
            PreparedStatement pstmt = cnn.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String email = rs.getString("email");
                emp = new Employee(id, name, age, email);
            }
            rs.close();
            pstmt.close();
            ConnectDB.disconnect();
        } catch (Exception e) {
            System.out.println("Error get list from db: " + e.getMessage());
        }
        return emp;
    }

}
