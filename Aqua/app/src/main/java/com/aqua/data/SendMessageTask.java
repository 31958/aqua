package com.aqua.data;

import android.os.AsyncTask;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class SendMessageTask extends AsyncTask<String, Void, String> {
        private Message message;
        private URL url;

        public void setMessage(Message message){
            this.message = message;
        }

        public void setUrl(URL url){
            this.url = url;
        }

        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection con = null;
            try {
                con = (HttpURLConnection) this.url.openConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                con.setRequestMethod("POST");
            } catch (ProtocolException e) {
                e.printStackTrace();
            }
            con.setDoOutput(true);

            Map<String,String> msg= new HashMap<>();
            msg.put("messageID","0");
            msg.put("senderID","0");
            msg.put("receiverID","1");
            msg.put("message",this.message.message);
            msg.put("dateTime","20:47 08/09/2020");

            con.setRequestProperty("Content-Type", "application/json");

            try(OutputStream os = con.getOutputStream()){
                System.out.println(getPostDataString(msg));
                os.write(getPostDataString(msg).getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                con.connect();
            } catch (IOException e) {
                e.printStackTrace();
            }
            InputStream s = null;
            try {
                s = con.getInputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                System.out.println(s.read());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "Data Inserted Successfully";
        }

    private String getPostDataString(Map<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for(Map.Entry<String, String> entry : params.entrySet()){
            if (first)
                first = false;
            else
                result.append("&");
            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }
        return result.toString();
    }
}
