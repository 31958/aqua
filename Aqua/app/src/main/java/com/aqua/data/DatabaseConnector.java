package com.aqua.data;

import android.widget.Toast;

import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
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
            }
        }
    }
}
