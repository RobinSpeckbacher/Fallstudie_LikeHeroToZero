package de.likeherotozero;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionTest {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/nutzer";
        String user = "root";
        String password = "admin";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            if (connection != null) {
                System.out.println("Drecksdrum is Connected");


                Statement statement = connection.createStatement();


                String query = "SELECT * FROM co2_emissionen";
                ResultSet resultSet = statement.executeQuery(query);


                while (resultSet.next()) {
                    String Land = resultSet.getString("Land");
                    System.out.println("Username: " + Land);
                }
            }
        } catch (SQLException e) {
            System.err.println("Drecksdrum is ned connected: " + e.getMessage());
        }
    }
}
