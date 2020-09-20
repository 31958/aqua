package com.aqua.data.oauth;

import android.os.AsyncTask;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.aqua.GlobalVariables;
import com.aqua.data.HttpPostRequest;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class RequestAccessToken extends AsyncTask<String, Void, String> {

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected String doInBackground(String... params) {
        String accessToken;
        RequestAccessToken(params[0], params[1], params[2]);
        return "";
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void RequestAccessToken(String client_id, String client_secret, String scope){
        Map<String,String> values = new HashMap<>();
        values.put("grant_type", "client_credentials");
        values.put("client_id", client_id);
        values.put("client_secret", client_secret);
        values.put("scope", scope);

        URL url = null;
        try {
            url = new URL(GlobalVariables.server);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        String response = HttpPostRequest.Post(url, values);

    }
}