package com.aqua.ui.post;

import android.content.Intent;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.aqua.R;
import com.aqua.ui.post.AddNewActivity;

public class MomentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moment);
    }

    public void addNew(View view) {
        Intent intentAdd = new Intent(this, AddNewActivity.class);
        startActivity(intentAdd);
    }

}