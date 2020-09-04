package com.aqua.ui.chat;

import com.aqua.R;

import com.aqua.data.Database;
import com.aqua.data.Message;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

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
                SendMessage();
            }
        });
    }

    private void SendMessage()
    {
        String message_content = textbox.getText().toString();

        Message message = new Message();

        Database.sendMessage(message_content);

        textbox.setText(null);
    }
}