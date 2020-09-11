package com.aqua.data;

import android.os.AsyncTask;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class SendMessageTask extends AsyncTask<String, Void, String> {
        private Message message;
        private URL url;

        public void setMessage(Message message){
            this.message = message;
        }

        public void setUrl(URL url){
            this.url = url;
        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        protected String doInBackground(String... params) {
            try{
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("POST");
                con.setDoOutput(true);

                Map<String,String> msg= new HashMap<>();
                msg.put("messageID",String.valueOf(this.message.getID()));
                msg.put("senderID",String.valueOf(this.message.getSenderID()));
                msg.put("receiverID",String.valueOf(this.message.getReceiverID()));
                msg.put("message",this.message.getMessage());
                msg.put("dateTime",this.message.getDateTime().toString());

                StringJoiner sj = new StringJoiner("&");
                for(Map.Entry<String,String> entry : msg.entrySet())
                    sj.add(URLEncoder.encode(entry.getKey(), "UTF-8") + "="
                            + URLEncoder.encode(entry.getValue(), "UTF-8"));

                byte[] out = sj.toString().getBytes();
                int length = out.length;

                con.setFixedLengthStreamingMode(length);
                con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
                con.connect();
                try(OutputStream os = con.getOutputStream()){
                    os.write(out);
                    // handle delivery errors returned from server here
                }
                InputStream s = con.getInputStream();
            }catch(Exception e){
                e.printStackTrace();
            }
            return "Data Inserted Successfully";
        }
}
