package com.aqua.data;

import android.os.AsyncTask;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class PostTask extends AsyncTask<String, Void, String> {
    private Post post;
    private URL url;

    public void setPost(Post post){
        this.post = post;
    }

    public void setUrl(URL url){
        this.url = url;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected String doInBackground(String... params) {
        Map<String,String> post = new HashMap<>();
        post.put("id",String.valueOf(this.post.getID()));
        post.put("author_id", String.valueOf(this.post.getAuthor_ID()));
        post.put("post_text",this.post.getPostText());
        post.put("post_image",this.post.getImageAsString());
        post.put("dateTime",this.post.getDateTime().toString());

        String response = HttpPostRequest.Post(this.url, post);

        //TODO: delete this
        System.out.println(response);
        return "";
    }
}