package com.aqua.data;

import android.accounts.AccountManager;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class HttpLogin {
    public String login(String client_id, String client_secret, String token) throws IOException {
        URL url = new URL("");
        URLConnection conn = (HttpURLConnection) url.openConnection();
        conn.addRequestProperty("client_id", client_id);
        conn.addRequestProperty("client_secret", client_secret);
        conn.setRequestProperty("Authorization", "OAuth " + token);
        return "";
    }
}
