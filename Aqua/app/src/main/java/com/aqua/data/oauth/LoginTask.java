package com.aqua.data.oauth;

import android.os.AsyncTask;

import org.dmfs.httpessentials.exceptions.ProtocolException;
import org.dmfs.oauth2.client.OAuth2AccessToken;

public class LoginTask extends AsyncTask<String, Void, String> {
    public static String username;
    public static String password;
    @Override
    protected String doInBackground(String... strings) {
        System.out.println("test");
        OAuth2AccessToken token = OAuthUtil.userResourceGrant(username, password);
        try {
            System.out.println(token.accessToken().toString());
        } catch (ProtocolException e) {
            e.printStackTrace();
        }
        return "error with login";
    }
}
