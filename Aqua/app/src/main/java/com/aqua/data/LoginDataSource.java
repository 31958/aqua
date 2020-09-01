package com.aqua.data;

import com.aqua.data.model.LoggedInUser;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    public Result<LoggedInUser> login(String username, String password) {
        Connection conn = null;
        String url = "jdbc:mysql://n1plcpnl0042.prod.ams1.secureserver.net:3306";
        try {
            try {
            // db parameters


            // create a connection to the database
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("successful");
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
            LoggedInUser fakeUser =
                    new LoggedInUser(
                            java.util.UUID.randomUUID().toString(), username);
            return new Result.Success<>(fakeUser);
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }
}
