package com.aqua.ui.post;

import android.content.Intent;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.aqua.R;

public class PostsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts);
    }

    public void addNew(View view) {
        Intent intentAdd = new Intent(this, NewPostActivity.class);
        startActivity(intentAdd);
    }

}