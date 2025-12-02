package com.pluralsight;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataManager {

    private BasicDataSource ds;

    public DataManager(BasicDataSource ds) {
        this.ds = ds;
    }

    public BasicDataSource getBasicDataSource() {
        return ds;
    }

    public List<Product> getAllProducts(BasicDataSource ds) throws SQLException {
        List<Product> products = new ArrayList<>();
        String query = "SELECT productID, productName, UnitPrice, UnitsInStock FROM products";

        try (
                Connection connection = ds.getConnection();
                PreparedStatement statement = connection.prepareStatement(query);
                ResultSet results = statement.executeQuery();
        ) {
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

    public List<Customer> getAllCustomers(BasicDataSource ds) throws SQLException {
        List<Customer> customers = new ArrayList<>();
        String query = " SELECT CompanyName, ContactName, City, Country, Phone FROM northwind.customers order by ContactName; ";

        try (
                Connection connection = ds.getConnection();
                PreparedStatement statement = connection.prepareStatement(query);
                ResultSet results = statement.executeQuery();
        ) {
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


    public List<Category> getAllCategories(BasicDataSource ds) throws SQLException {
        List<Category> categories = new ArrayList<>();
        String query = "SELECT categoryID, categoryName FROM categories ORDER BY categoryID";

        try (
                Connection connection = ds.getConnection();
                PreparedStatement statement = connection.prepareStatement(query);
                ResultSet results = statement.executeQuery();
        ) {
            while (results.next()) {
                int categoryID = results.getInt("categoryID");
                String categoryName = results.getString("categoryName");

                Category c = new Category(categoryID, categoryName);
                categories.add(c);
            }
        }

        return categories;
    }

    public List<Product> getProductsByCategoryID(BasicDataSource ds, String categoryID) throws SQLException {
        List<Product> products = new ArrayList<>();
        try (
                Connection connection = ds.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "SELECT productID, productName, UnitPrice, unitsInStock FROM products WHERE categoryID = ? ")
        ){

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
