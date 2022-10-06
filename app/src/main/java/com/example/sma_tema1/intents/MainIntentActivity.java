package com.example.sma_tema1.intents;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sma_tema1.R;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainIntentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_intent);

        Button button1 = (Button) findViewById(R.id.button);
        Button button2 = (Button) findViewById(R.id.button2);
        Button button3 = (Button) findViewById(R.id.button3);
        Button button4 = (Button) findViewById(R.id.button4);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent();
                intent1.setAction(android.content.Intent.ACTION_VIEW);
                intent1.setData(Uri.parse("http://www.google.com"));
                startActivity(intent1);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2=new Intent();
                intent2.setAction(android.content.Intent.ACTION_VIEW);
                intent2.setData(Uri.parse("tel:00401213456"));
                startActivity(intent2);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3=new Intent();
                intent3.setAction(Intent.CATEGORY_LAUNCHER);
                intent3.setData(Uri.parse("http://www.google.com"));
                startActivity(intent3);
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent4=new Intent();
                intent4.setAction(Intent.CATEGORY_LAUNCHER);
                intent4.setData(Uri.parse("http://www.google.com"));
                startActivity(intent4);
            }
        });
    }
}