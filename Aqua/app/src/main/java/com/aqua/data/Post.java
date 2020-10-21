package com.aqua.data;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Build;
import android.util.Base64;
import androidx.annotation.RequiresApi;

import java.io.ByteArrayOutputStream;
import java.time.ZonedDateTime;

public class Post {
    private int ID;
    private String imageUrl;
    private String postText;

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
