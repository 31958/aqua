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
    private int author_ID;
    private String postText;
    private String postImage;
    private ZonedDateTime dateTime;

    private static final float PREFERRED_WIDTH = 250;
    private static final float PREFERRED_HEIGHT = 250;

    public static final int COL_ID = 0;
    public static final int COL_TEXT = 1;
    public static final int COL_IMAGE = 2;


    @RequiresApi(api = Build.VERSION_CODES.O)
    public Post(String postText, Bitmap postImage) {
        this.ID = 1;
        this.author_ID = 100;
        this.postText = postText;
        this.postImage = bitmapToString(resizeBitmap(postImage));
        this.dateTime = ZonedDateTime.now();
    }


    public Post(Cursor cursor) {
        this.postText = cursor.getString(COL_TEXT);
        this.postImage = cursor.getString(COL_IMAGE);
    }

    public int getID(){
        return this.ID;
    }


    public int getAuthor_ID() {
        return this.author_ID;
    }


    public String getPostText(){
        return this.postText;
    }


    public Bitmap getPostImage() {
        return stringToBitmap(this.postImage);
    }

    public String getImageAsString() {
        return this.postImage;
    }

    public ZonedDateTime getDateTime(){
        return this.dateTime;
    }


    private static String bitmapToString(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        return Base64.encodeToString(b, Base64.DEFAULT);
    }


    private static Bitmap stringToBitmap(String encodedString) {
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            return BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }


    public static Bitmap resizeBitmap(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float scaleWidth = PREFERRED_WIDTH / width;
        float scaleHeight = PREFERRED_HEIGHT / height;

        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap resizedBitmap = Bitmap.createBitmap(
                bitmap, 0, 0, width, height, matrix, false);
        bitmap.recycle();
        return resizedBitmap;
    }
}
