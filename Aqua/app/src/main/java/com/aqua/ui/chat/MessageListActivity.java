package com.aqua.ui.chat;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.aqua.R;
import com.aqua.data.Message;
import com.aqua.data.RetrieveMessageTask;
import com.aqua.data.SendMessageTask;
import com.aqua.data.chat.MessageListAdapter;
import com.aqua.data.oauth.LoggedInUser;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MessageListActivity extends AppCompatActivity {
    private List<Message> messageList = new ArrayList<>();
    private RecyclerView mMessageRecycler;
    private MessageListAdapter mMessageAdapter;
    private Button sendButton;
    private EditText textbox;
    private int receiverID;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_list);

        Message testmsg1 = new Message(), testmsg2 = new Message();
        testmsg1.compose("hello", this.receiverID);
        testmsg2.compose("bye", 123123, LoggedInUser.user.getID());
        messageList.add(testmsg1);
        messageList.add(testmsg2);

        mMessageRecycler = (RecyclerView) findViewById(R.id.reyclerview_message_list);
        mMessageAdapter = new MessageListAdapter(this, messageList);
        mMessageRecycler.setHasFixedSize(false);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mMessageRecycler.setLayoutManager(linearLayoutManager);

        mMessageRecycler.setAdapter(mMessageAdapter);

        System.out.println(mMessageAdapter.getItemCount());
        sendButton = findViewById(R.id.button_chatbox_send);
        textbox = findViewById(R.id.edittext_chatbox);

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
            sendMessageTask.setUrl(new URL(getString(R.string.post_message)));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        messageList.add(msg);
        sendMessageTask.execute();
    }

    // TODO: add security to this function using oauth
    private void retrieveMessage(){

        RetrieveMessageTask retrieveMessageTask = new RetrieveMessageTask();
        try {
            retrieveMessageTask.setUrl(new URL(getString(R.string.get_message)));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        retrieveMessageTask.execute();
    }

    public void setReceiverID(int receiverID) {
        this.receiverID = receiverID;
    }

    private void displayMessages(){

    }
}