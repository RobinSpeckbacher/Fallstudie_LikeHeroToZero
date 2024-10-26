package de.likeherotozero;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionTest {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/c02_data";
        String user = "root";
        String password = "your_password_here"; // Replace with your actual password

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            if (connection != null) {
                System.out.println("Connected to the database!");
            }
        } catch (SQLException e) {
            System.err.println("Connection failed: " + e.getMessage());
        }
    }
}
