package com.pluralsight;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static String username = "root";
    private static String password = "yearup";

    // load the driver


    // create the connection and prepared statement


    public static void main (String[] args) {


    while(true) {
        System.out.println("""
                What do you want to do?
                 1) Display all products
                 2) Display all customers
                 0) Exit
                Select an option:""");

        int choice = ConsoleHelper.promptForInt("");
        try{
            switch (choice) {
                case 1 -> displayAllProducts();
                case 2 -> displayAllCustomers();
                case 0 -> {
                    return;
                }
            }
        }
        catch(Exception e){
            System.out.println("there was an error");
        }
    }









    }
    private static void displayAllProducts() throws ClassNotFoundException, SQLException {
        List<Product> products = new ArrayList<>();

        //load mysql driver
        Class.forName("com.mysql.cj.jdbc.Driver");

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

            Product p = new Product(productID, productName, unitPrice, unitsInStock);
            products.add(p);
        }
            products.forEach(System.out::println);
    }

    private static void displayAllCustomers() throws ClassNotFoundException, SQLException {
        List<Customer> customers = new ArrayList<>();

        //load mysql driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/northwind", username, password);
        Statement statement = connection.createStatement();

        String query = """
        SELECT
        
        CompanyName, ContactName, City, Country, Phone
        
        FROM northwind.customers
        left join orders on customers.CustomerID = orders.customerid
        order by ContactName;
        """;

// 2. Execute your query
        ResultSet results = statement.executeQuery(query);

// process the results
        while (results.next()) {
            String companyName = results.getString("CompanyName");
            String contactName = results.getString("ContactName");
            String city = results.getString("City");
            String country = results.getString("Country");
            String phone = results.getString("Phone");

            Customer c = new Customer(companyName, contactName, city, country, phone);
            customers.add(c);
        }
        customers.forEach(System.out::println);
    }



}
