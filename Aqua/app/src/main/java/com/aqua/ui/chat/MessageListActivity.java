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

import com.aqua.R;
import com.aqua.data.chat.MessageListAdapter;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
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

        String server = "java-1b.cl.cam.ac.uk";
        int port = 15003;
        final Socket s;
        try {
            s = new Socket(server, port);
        } catch (IOException e) {
            System.err.println("Cannot connect to " + server + " on port " + port);
            return;
        }

        Thread output =
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            DynamicObjectInputStream objectinputstream = new DynamicObjectInputStream(s.getInputStream());
                            while(true){
                                Object msg = objectinputstream.readObject();
                                if(msg instanceof RelayMessage){
                                    RelayMessage rm = (RelayMessage) msg;
                                    messageList.add(rm);
                                }
                            }
                        } catch (Exception e) {
                            System.err.println(e.getMessage());
                        }
                    }
                };
        output.setDaemon(true);
        output.start();

        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(s.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        ObjectOutputStream finalOut = out;
        sendButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                ChatMessage cm = new ChatMessage(textbox.getText().toString());
                messageList.add(cm);
                try {
                    finalOut.writeObject(cm);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}