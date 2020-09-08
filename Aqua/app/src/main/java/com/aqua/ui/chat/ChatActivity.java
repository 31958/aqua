package com.aqua.ui.chat;

import com.aqua.R;

import com.aqua.data.Message;
import com.aqua.data.SendMessageTask;

import androidx.appcompat.app.AppCompatActivity;

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

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        sendButton = findViewById(R.id.sendButton);
        textbox = findViewById(R.id.textbox);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SendMessage(textbox.getText().toString());
            }
        });
    }

    private void SendMessage(String message)
    {
        Message msg = new Message();
        msg.ID = "0";
        msg.from_ID = "0";
        msg.to_ID = "1";
        msg.message = message;
        msg.dateTime = "19:17 08/09/2020";

        SendMessageTask sendMessageTask = new SendMessageTask();
        sendMessageTask.setMessage(msg);
        try {
            sendMessageTask.setUrl(new URL("http://elitegateinternational.co.uk/message.php"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        sendMessageTask.execute();
    }

    public void showPopup(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}