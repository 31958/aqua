package com.aqua.data.oauth;

import android.os.AsyncTask;

import org.dmfs.oauth2.client.OAuth2AccessToken;

public class LoginTask extends AsyncTask<String, Void, String> {
    @Override
    protected String doInBackground(String... strings) {
        OAuth2AccessToken token;
        return token = OAuthUtil.userResourceGrant(username, password);
        ;
    }
}
