package com.aqua.data;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;
import java.util.StringJoiner;

public class HttpGetRequest {
     @RequiresApi(api = Build.VERSION_CODES.N)
     public static String Get(URL url, Map<String,String> values){
            try{
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                con.setDoOutput(true);

                byte[] out = MapToPostData(values);

                int length = out.length;

                con.setFixedLengthStreamingMode(length);
                con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
                con.connect();
                try(OutputStream os = con.getOutputStream()){
                    os.write(out);
                }

                Integer responseCode = con.getResponseCode();
                System.out.println("Response Code : " + responseCode);

                if (responseCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader inputReader = new BufferedReader(
                            new InputStreamReader(con.getInputStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();

                    while ((inputLine = inputReader.readLine()) != null) {
                        response.append(inputLine);
                    }

                    inputReader.close();
                    return response.toString();
                }else{
                    return "";
                }
            }catch(Exception e){
                e.printStackTrace();
            }
            return null;
     }

     @RequiresApi(api = Build.VERSION_CODES.N)
     private static byte[] MapToPostData(Map<String,String> map) throws UnsupportedEncodingException {
         StringJoiner sj = new StringJoiner("&");
         for(Map.Entry<String,String> entry : map.entrySet())
                sj.add(URLEncoder.encode(entry.getKey(), "UTF-8") + "="
                        + URLEncoder.encode(entry.getValue(), "UTF-8"));
            return sj.toString().getBytes();
     }
}

