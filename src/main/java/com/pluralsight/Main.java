package com.pluralsight;

import java.sql.*;

public class Main {
    public static void main (String[] args) throws ClassNotFoundException, SQLException {

        String username = "root";
        String password = "yearup";

        // load the driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        // create the connection and prepared statement
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/northwind", username, password);

        Statement statement = connection.createStatement();

        String query = """
                SELECT
                productID, productName, UnitPrice, UnitsInStock
                
                FROM products""";

        ResultSet results = statement.executeQuery(query);

        while (results.next()) {
            int productID = results.getInt("ProductID");
            String productName = results.getString("ProductName");
            double unitPrice = results.getDouble("UnitPrice");
            int unitsInStock = results.getInt("UnitsInStock");

            System.out.printf("%-10d %-30s %-10.2f %-10d\n",productID, productName, unitPrice, unitsInStock);

        }





    }
}
