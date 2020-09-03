package com.aqua.data;

import android.os.Build;
import android.provider.ContactsContract;

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
        try {
            Database.connect(username, password);
            Database.sendMessage("login successful");
            LoggedInUser fakeUser =
                    new LoggedInUser(
                            java.util.UUID.randomUUID().toString(), username);
            return new Result.Success<>(fakeUser);
        } catch (SQLException e) {
            return new Result.Error(new SQLException("SQL Error", e));
        }
    }

    public void logout(){
        Database.disconnect();
    }
}
