package com.aqua.data;

import android.os.AsyncTask;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.net.URL;
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

        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        protected String doInBackground(String... params) {
            Map<String,String> msg= new HashMap<>();
            msg.put("messageID",String.valueOf(this.message.getID()));
            msg.put("senderID",String.valueOf(this.message.getSenderID()));
            msg.put("receiverID",String.valueOf(this.message.getReceiverID()));
            msg.put("message",this.message.getMessage());
            msg.put("dateTime",this.message.getDateTime().toString());

            String response = HttpPostRequest.Post(this.url, msg);

            //TODO: delete this
            System.out.println(response);
            return "";
        }
}
