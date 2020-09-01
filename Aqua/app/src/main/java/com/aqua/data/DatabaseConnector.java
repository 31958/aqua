package com.aqua.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
    public static void main(String[] args) {
        Connection conn = null;
        String url = "jdbc:mysql://n1plcpnl0042.prod.ams1.secureserver.net:3306";
        String user = "admin";
        String password = "password";
        try {
            // db parameters


            // create a connection to the database
            conn = DriverManager.getConnection(url, user, password);
            // more processing here
            // ...
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try{
                if(conn != null)
                conn.close();
            }catch(SQLException ex){
                System.out.println(ex.getMessage());
            }
        }
    }
}
