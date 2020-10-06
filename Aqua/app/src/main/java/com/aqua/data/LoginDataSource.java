package com.aqua.data;

import com.aqua.data.oauth.LoggedInUser;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    public Result<LoggedInUser> login(String username, String password) {
        //Database.connect(username, password);
        //Database.sendMessage("login successful");
        LoggedInUser.user = new User(0,"Annonymous");
        return new Result.Success<>(new LoggedInUser());
    }

    public void logout(){
        //Database.disconnect();
    }
}
