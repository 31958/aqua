package com.aqua.ui.chat;

import com.aqua.R;

import com.aqua.data.Message;
import com.aqua.data.SendMessageTask;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.net.MalformedURLException;
import java.net.URL;

public class ChatActivity extends AppCompatActivity
{
    private ImageButton sendButton;
    private EditText textbox;
    private int receiverID;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        sendButton = findViewById(R.id.sendButton);
        textbox = findViewById(R.id.textbox);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                sendMessage(textbox.getText().toString());
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void sendMessage(String message)
    {
        Message msg = new Message();
        msg.compose(message,this.receiverID);

        SendMessageTask sendMessageTask = new SendMessageTask();
        sendMessageTask.setMessage(msg);
        try {
            sendMessageTask.setUrl(new URL("http://elitegateinternational.co.uk/message.php"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        sendMessageTask.execute();
    }

    public void setReceiverID(int receiverID) {
        this.receiverID = receiverID;
    }

    private void displayMessages(){

    }

    public void showPopup(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}