package com.aqua.ui.post;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.aqua.R;
import com.aqua.data.Post;

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
            @Override
            public void onClick(View view) {

                String postText = postEdit.getText().toString(); //Get txt

                Post post = new Post();
                post.ID = "1";
                post.author_ID = "1";
                post.content = "Look, my father just died.";
                post.dateTime = "yesterday";
                post.location = "Wuhan";


            }
        });

    }

}