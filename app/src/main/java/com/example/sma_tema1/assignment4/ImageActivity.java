package com.example.sma_tema1.assignment4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.sma_tema1.R;

public class ImageActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        MyApplication myApplication = new MyApplication();

        if(myApplication.getBitmap() == null)
        {
            Toast.makeText(this, "Error transmitting URL", Toast.LENGTH_SHORT).show();
        }
        else {
            ImageView imageView = (ImageView) findViewById(R.id.imageView);
            imageView.setImageBitmap(myApplication.getBitmap());
        }
    }

}