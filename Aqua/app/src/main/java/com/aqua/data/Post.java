package com.aqua.data;

import android.os.Build;
import androidx.annotation.RequiresApi;

import java.time.ZonedDateTime;

public class Post {
    public int ID;
    public int author_ID;
    public String postContent;
    public ZonedDateTime dateTime;
    //location


    @RequiresApi(api = Build.VERSION_CODES.O)
    public Post(String postContent) {
        this.ID = RequestMessageID.requestID();
        this.postContent = postContent;
        this.author_ID = CurrentUser.getID();
        this.dateTime = ZonedDateTime.now();
    }


    public int getID(){
        return this.ID;
    }


    public int getAuthor_ID() {
        return this.author_ID;
    }


    public String getPostContent(){
        return this.postContent;
    }


    public ZonedDateTime getDateTime(){
        return this.dateTime;
    }
}
