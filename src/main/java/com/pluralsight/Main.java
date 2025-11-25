package com.pluralsight;

import java.sql.*;

public class Main {

    private static String username = "root";
    private static String password = "yearup";

    // load the driver


    // create the connection and prepared statement


    public static void main (String[] args) throws ClassNotFoundException, SQLException {

        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/northwind", username, password);
        Statement statement = connection.createStatement();
        Class.forName("com.mysql.cj.jdbc.Driver");




    while(true) {
        System.out.println("""
                What do you want to do?
                 1) Display all products
                 2) Display all customers
                 0) Exit
                Select an option:""");

        int choice = ConsoleHelper.promptForInt("");
        switch (choice) {
            case 1 -> displayAllProducts();
            case 2 -> displayAllCustomers();
            case 0 -> return;
        }
    }




        String query2 = """
        SELECT
        
        CompanyName, ContactName, orders.orderid
        
        FROM northwind.customers
        left join orders on customers.CustomerID = orders.customerid
        order by ContactName;
        """;

// 2. Execute your query
        ResultSet results2 = statement.executeQuery(query);

// process the results
        while (results2.next()) {
            String companyName = results2.getString("CompanyName");
            String contactName = results2.getString("ContactName");
            int orderId = results2.getInt("orderid");

            System.out.printf("%-30s %-30s %20d\n", companyName, contactName, orderId);

            // System.out.println(companyName);
        }




    }
    private void displayAllProducts() {
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

            System.out.printf("%-10d %-30s %-10.2f %-10d\n", productID, productName, unitPrice, unitsInStock);

        }
    }
}
