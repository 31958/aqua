package com.aqua.data;

import android.os.AsyncTask;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
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


            Map<String,String> msg= new HashMap<>();
            msg.put("messageID",message.ID);
            msg.put("senderID",message.from_ID);
            msg.put("receiverID",message.to_ID);
            msg.put("message",message.message);
            msg.put("dateTime",message.dateTime);

            String[] data = {
                    this.message.ID,
                    this.message.from_ID,
                    this.message.to_ID,
                    this.message.message,
                    this.message.dateTime
            };

            OutputStream out = null;

            try {
                HttpURLConnection con = (HttpURLConnection) url.openConnection();

                con.setRequestMethod("POST");

                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(con.getOutputStream(), "UTF-8"));

                writer.write(getPostDataString(msg));

                writer.flush();
                writer.close();
                out.close();

                BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                StringBuilder sb = new StringBuilder();

                String json;
                while ((json = reader.readLine()) != null) {
                    sb.append(json + "\n");
                }

                System.out.println("JSON");
                System.out.println(json);
                System.out.println("JSON");

                con.connect();

            } catch (Exception e){
                e.printStackTrace();
            }

            System.out.println("post requested");
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
