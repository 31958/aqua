package com.aqua.data;

import com.aqua.ui.DrawerActivity;
import com.aqua.ui.login.LoginActivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    private static Connection conn = null;

    public static void connect(String username, String password) throws SQLException {
        String url = "jdbc:mysql://n1plcpnl0042.prod.ams1.secureserver.net/aquadb";
        // db parameters
        // create a connection to the database
        conn = DriverManager.getConnection(url, username, password);
        System.out.println("successful");
        // more processing here
        // ...
    }

    public static void disconnect() {
        try{
            if(conn != null)
                conn.close();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }

    public static void sendMessage(String message){
        try{
            Statement stmt = conn.createStatement();

            String sql = "INSERT INTO test " +
                    "VALUES ('Sender', 'Receiver', '"+ message+"')";
            stmt.executeUpdate(sql);
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
