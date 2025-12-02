package com.pluralsight;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Main {

//    private static String username = "root";
//    private static String password = "yearup";
//    private static String database = "northwind";
//    private static String url = "jdbc:mysql://localhost:3306/" + database;


    public static void main (String[] args) {

        try{
            BasicDataSource ds = getBasicDataSource(args);

            DataManager dm = new DataManager(ds);

            UserInterface userInterface = new UserInterface(dm);

            userInterface.start();

        }
        catch(Exception e){
            System.out.println("There was a general error: " + e.getMessage() );
        }
    }




    private static BasicDataSource getBasicDataSource(String username, String password, String URL){
        BasicDataSource ds = new BasicDataSource();
        ds.setUrl(URL);
        ds.setPassword(password);
        ds.setUsername(username);
        return ds;
    }

    private static BasicDataSource getBasicDataSource(String[] args){
        String username = args[0];
        String password = args[1];
        String URL = args[2];
        return getBasicDataSource(username, password, URL);
    }







}
