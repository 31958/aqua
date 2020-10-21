package com.aqua.data;

import android.os.AsyncTask;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class UploadPostTask extends AsyncTask<String, Void, String> {
    private Post post;
    private URL url;

    public void setPost(Post post) {
        this.post = post;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    @Override
    protected String doInBackground(String... strings) {
        Map<String, String> post = new HashMap<>();
        post.put("imageUrl", String.valueOf(this.post.getImageUrl()));
        post.put("postText", String.valueOf(this.post.getPostText()));

        String response = HttpPostRequest.Post(this.url, post);
        System.out.println(response);

        return null;
    }
}
