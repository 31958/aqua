package com.aqua.data.moment;

import android.os.AsyncTask;
import com.aqua.data.HttpPostRequest;
import com.aqua.data.moment.Post;

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
        post.put("id", null);
        post.put("url", String.valueOf(this.post.getImageUrl()));
        post.put("name", String.valueOf(this.post.getPostText()));

        String response = HttpPostRequest.Post(this.url, post);
        System.out.println(response);

        return null;
    }
}
