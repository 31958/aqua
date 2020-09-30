package com.aqua.ui.post;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.aqua.R;
import com.aqua.data.Post;
import com.aqua.data.PostTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class NewPostActivity extends AppCompatActivity {

    private static final int GALLERY_REQUEST_CODE = 100;
    private static final int CAMERA_REQUEST_CODE = 200;

    //components
    private EditText postEdit;
    private Button sendButton;
    private ImageView postImage;
    private Button galleryBtn;
    private Button cameraBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post);

        //Initialize
        postEdit = findViewById(R.id.postEdit);
        sendButton = findViewById(R.id.sendButton);
        postImage = findViewById(R.id.postImage);
        galleryBtn = findViewById(R.id.pickImageButton);
        cameraBtn = findViewById(R.id.cameraBtn);

        //Gallery listener
        galleryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery(v);
            }
        });

        //Camera listener
        cameraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCamera(v);
            }
        });

        //Sending listener
        sendButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                String postText = postEdit.getText().toString(); //Get txt
                uploadPost(postText, getBitmap());
            }
        });
    }

    public void openGallery(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select a picture"), GALLERY_REQUEST_CODE);
    }

    public void openCamera(View view) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, CAMERA_REQUEST_CODE);
        }
    }

    //Sending method
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void uploadPost(String postText, Bitmap postImage) {
        Post post = new Post(postText, postImage);

        PostTask postTask = new PostTask();
        postTask.setPost(post);

        try {
            postTask.setUrl(new URL(getString(R.string.upload_post)));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        postTask.execute();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //For gallery
        if (resultCode == RESULT_OK && requestCode == GALLERY_REQUEST_CODE) {
            try {
                Uri selectedImage = data.getData();
                InputStream imageStream = getContentResolver().openInputStream(selectedImage);
                postImage.setImageBitmap(BitmapFactory.decodeStream(imageStream));
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }

        //For camera
        if (requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            postImage.setImageBitmap(imageBitmap);
        }
    }

    public Bitmap getBitmap() {
        postImage.invalidate();
        BitmapDrawable drawable = (BitmapDrawable) postImage.getDrawable();
        return drawable.getBitmap();
    }
}