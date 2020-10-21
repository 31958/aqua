package com.aqua.data;

public class Post {
    private int ID;
    private final String imageUrl;
    private final String postText;

    public Post(String imageUrl, String postText) {
        this.imageUrl = imageUrl;
        this.postText = postText;
    }

    public String getPostText() {
        return postText;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
