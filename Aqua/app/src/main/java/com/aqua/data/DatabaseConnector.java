package com.aqua.data;

<<<<<<< HEAD
import android.widget.Toast;

=======
import java.sql.Connection;
>>>>>>> c2b2e2448c79f319fca30179873b9e9572a12b87
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
<<<<<<< HEAD
    public void conectarBDMySQL (String user, String password,
                                 String ip, String port, String cat)
    {

        if (conexionMySQL == null)
        {
            String urlConexionMySQL = "";
            if (cat!= "")
                urlConexionMySQL = "jdbc:mysql://" + ip + ":" + port+ "/" + cat;
            else
                urlConexionMySQL = "jdbc:mysql://" + ip + ":" + port;
            if (user!= "" & password!= "" & ip != "" & port!= "")
            {
                try
                {
                    Class.forName("com.mysql.jdbc.Driver");
                    conexionMySQL = DriverManager.getConnection(urlConexionMySQL,
                            user, password);
                }
                catch (ClassNotFoundException e)
                {
                    Toast.makeText(getApplicationContext(),
                            "Error: " + e.getMessage(),
                            Toast.LENGTH_SHORT).show();
                }
                catch (SQLException e)
                {
                    Toast.makeText(getApplicationContext(),
                            "Error: " + e.getMessage(),
                            Toast.LENGTH_SHORT).show();
                }
=======
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
>>>>>>> c2b2e2448c79f319fca30179873b9e9572a12b87
            }
        }
    }
}
