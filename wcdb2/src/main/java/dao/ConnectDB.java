package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/wcdb2db";
    private static final String JDBC_USERNAME = "root";
    private static final String JDBC_PASSWORD = "";
    private static Connection conn;

    public static Connection connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
            System.out.println("Connect thanh cong");

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error connect: " + e.getMessage());
        }
        return conn;
    }

    public static void disconnect() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("Error connect: " + e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        ConnectDB.connect();
    }
}
