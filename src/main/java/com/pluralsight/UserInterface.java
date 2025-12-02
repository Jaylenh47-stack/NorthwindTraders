package com.pluralsight;

import java.sql.SQLException;
import java.util.List;

public class UserInterface {

    private DataManager dm;

    public UserInterface(DataManager dm) {
        this.dm = dm;
    }

    public void start(){
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
            switch (choice) {
                case 1 -> processDisplayAllProductsRequest();
                case 2 -> processDisplayAllCustomersRequest();
                case 3 -> processDisplayAllCategoriesRequest();
                case 0 -> {return;}
                }        
            }
            
        
    }

    private void processDisplayAllCategoriesRequest() {
        try{
            List<Category> categories = dm.getAllCategories(dm.getBasicDataSource());
            ConsoleHelper.displayList(categories);

            boolean yesOrNO = ConsoleHelper.promptForYesNo("Would you like to see all products from a category");
            if (yesOrNO){
                String categoryName = ConsoleHelper.promptForString("Enter categoryID");
               List<Product> productsOfCategory = dm.getProductsByCategoryID(dm.getBasicDataSource(), categoryName);
               ConsoleHelper.displayList(productsOfCategory);
            }
        }
        catch(SQLException e){
            System.out.println("There was a SQL error: " + e.getMessage());
        }
    }

    private void processDisplayAllCustomersRequest() {
        try {
            List<Customer> customers =dm.getAllCustomers(dm.getBasicDataSource());
            ConsoleHelper.displayList(customers);
        }
        catch (SQLException e) {
            System.out.println("There was a SQL error: " + e.getMessage());
        }
    }

    public void processDisplayAllProductsRequest(){
        try {
            List<Product> products =dm.getAllProducts(dm.getBasicDataSource());
            ConsoleHelper.displayList(products);
        }
        catch (SQLException e) {
            System.out.println("There was a SQL error: " + e.getMessage());
        }
    }
        
    
        
}
