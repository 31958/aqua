package com.aqua.data;

import android.os.AsyncTask;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class RetrieveMessageTask extends AsyncTask<String, Void, String> {
    private URL url;

    public void setUrl(URL url){
        this.url = url;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected String doInBackground(String... strings) {
        Map<String,String> params = new HashMap<>();
        params.put("senderID", "0");

        String response = HttpGetRequest.Get(this.url, params);

        System.out.println(response);
        return "";
    }
}
