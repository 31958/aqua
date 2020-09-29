package com.aqua.ui.post;

import android.os.Build;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.aqua.R;
import com.aqua.data.Post;
import com.aqua.data.PostTask;

import java.net.MalformedURLException;
import java.net.URL;

public class NewPostActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post);


        //Get components
        final EditText postEdit = findViewById(R.id.postEdit);
        final Button sendButton = findViewById(R.id.sendButton);
        final TextView textView = findViewById(R.id.textView2);

        //Set sending listener
        sendButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {

                String postText = postEdit.getText().toString(); //Get txt
                uploadPost(postText);

            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void uploadPost(String postText) {
        Post post = new Post(postText);

        PostTask postTask = new PostTask();
        postTask.setPost(post);

        try {
            postTask.setUrl(new URL(getString(R.string.upload_post)));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        postTask.execute();
    }
}