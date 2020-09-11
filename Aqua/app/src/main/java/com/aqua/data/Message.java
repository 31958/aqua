package com.aqua.data;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.ZonedDateTime;

public class Message {
    private int ID;
    private int receiverID;
    private int senderID;
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
        return this.senderID;
    }

    public String getMessage(){
        return this.message;
    }

    public ZonedDateTime getDateTime(){
        return this.dateTime;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void compose(String message, int receiverID){
        this.ID = RequestMessageID.requestID();
        this.message = message;
        this.senderID = CurrentUser.getID();
        this.receiverID = receiverID;
        this.dateTime = ZonedDateTime.now();
    }
}
