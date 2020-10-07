package com.aqua.data;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.aqua.data.oauth.LoggedInUser;

import java.time.ZonedDateTime;

public class Message {
    private int ID;
    private int receiverID;
    private User sender;
    private String message;
    private ZonedDateTime dateTime;

    public Message() {
    }

    public int getID(){
        return this.ID;
    }

    public int getReceiverID(){
        return this.receiverID;
    }

    public int getSenderID(){
        return this.sender.getID();
    }

    public String getMessage(){
        return this.message;
    }

    public ZonedDateTime getDateTime(){
        return this.dateTime;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void compose(String message, int senderID, int receiverID){
        this.ID = RequestMessageID.requestID();
        this.message = message;
        this.sender = new User(senderID, "Yuhan");
        this.receiverID = receiverID;
        this.dateTime = ZonedDateTime.now();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void compose(String message, int receiverID){
        this.ID = RequestMessageID.requestID();
        this.message = message;
        this.sender = LoggedInUser.user;
        this.receiverID = receiverID;
        this.dateTime = ZonedDateTime.now();
    }

    public User getSender() {
        return sender;
    }
}
