package com.example.sma_tema1.assignment4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.sma_tema1.R;

public class ImageActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
    }
    MyApplication myApplication = (MyApplication) getApplicationContext();

//    if(myApplication.getBitmap() == null)
//    {
//        Toast.makeText(this, "Error at transmitting url!", Toast.LENGTH_SHORT).show();
//        finish();
//    }
//    else
//    {
//        ImageView image = (ImageView) findViewById(R.id.imageView);
//        image.setImageBitmap(myApplication.getBitmap());
//    }
}