package com.pluralsight;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static String username = "root";
    private static String password = "yearup";
    private static String database = "northwind";
    private static String url = "jdbc:mysql://localhost:3306/" + database;


    public static void main (String[] args) {

    while(true) {
        System.out.println("""
                What do you want to do?
                ========================
                 1) Display all products
                 2) Display all customers
                 3) Display all categories
                 0) Exit
                Select an option:""");

        int choice = ConsoleHelper.promptForInt("");

        try{
            switch (choice) {
                case 1 ->  getAllProducts().forEach(System.out::println);
                case 2 -> getAllCustomers().forEach(System.out::println);
                case 3 ->{
                    getAllCategories().forEach(System.out::println);
                    boolean yesOrNO = ConsoleHelper.promptForYesNo("Would you like to see all products from a category");
                    if (yesOrNO){
                        String categoryName = ConsoleHelper.promptForString("Enter categoryID");
                        getProductsByCategoryID(categoryName).forEach(System.out::println);
                    }

                }

                case 0 -> {
                    return;
                }
            }
        }
        catch(ClassNotFoundException e){
            System.out.println("There was a problem loading the driver: " + e.getMessage());
        }
        catch(SQLException e){
            System.out.println("There was a problem with the database " + e.getMessage());
        }
        catch(Exception e){
            System.out.println("There was a general error: " + e.getMessage() );
        }
    }


    }


    private static List<Product> getAllProducts() throws ClassNotFoundException, SQLException {
        List<Product> products = new ArrayList<>();
        String query = "SELECT productID, productName, UnitPrice, UnitsInStock FROM products";

        Class.forName("com.mysql.cj.jdbc.Driver");

        try(Connection connection = DriverManager.getConnection(
                url, username, password);
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(query);)
        {

        while (results.next()) {
            int productID = results.getInt("ProductID");
            String productName = results.getString("ProductName");
            double unitPrice = results.getDouble("UnitPrice");
            int unitsInStock = results.getInt("UnitsInStock");

            Product p = new Product(productID, productName, unitPrice, unitsInStock);
            products.add(p);
        }
    }
            return products;
    }

    private static List<Customer> getAllCustomers() throws ClassNotFoundException, SQLException {
        List<Customer> customers = new ArrayList<>();

        String query = " SELECT CompanyName, ContactName, City, Country, Phone FROM northwind.customers order by ContactName; ";

        Class.forName("com.mysql.cj.jdbc.Driver");

        try(Connection connection = DriverManager.getConnection(
                url, username, password);
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(query);)
        {

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
    }

        return customers;
    }

    private static List<Category> getAllCategories() throws SQLException, ClassNotFoundException {
        List<Category> categories = new ArrayList<>();

        String query = "SELECT categoryID, categoryName FROM categories ORDER BY categoryID";

        Class.forName("com.mysql.cj.jdbc.Driver");

        try(Connection connection = DriverManager.getConnection(
                url, username, password);
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(query);)
        {
            while(results.next()) {
                int categoryID = results.getInt("categoryID");
                String categoryName = results.getString("categoryName");

                Category c = new Category(categoryID, categoryName);
                categories.add(c);
            }
        }
        return categories;
    }

    private static List<Product> getProductsByCategoryID(String categoryID) throws SQLException, ClassNotFoundException {
        List<Product> products = new ArrayList<>();

        Class.forName("com.mysql.cj.jdbc.Driver");

        try(Connection connection = DriverManager.getConnection(
                url, username, password);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT productID, productName, UnitPrice, unitsInStock" +
                    " FROM products WHERE categoryID = ? "))
        {
            preparedStatement.setString(1, categoryID);

           try(ResultSet results = preparedStatement.executeQuery()){

               while (results.next()) {
                   int productID = results.getInt("ProductID");
                   String productName = results.getString("ProductName");
                   double unitPrice = results.getDouble("UnitPrice");
                   int unitsInStock = results.getInt("UnitsInStock");

                   Product p = new Product(productID, productName, unitPrice, unitsInStock);
                   products.add(p);
               }
           }
        }
        return products;
    }


}
