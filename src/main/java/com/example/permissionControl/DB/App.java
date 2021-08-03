package com.example.permissionControl.DB;



import java.sql.*;


public class App {

    public Connection connect() {
         final String url = "jdbc:postgresql://localhost/permissioncontroller";
         final String user = "postgres";
         final String password = "14024emre";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }



}
