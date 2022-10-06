package com.example.sma_tema1.intents;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sma_tema1.R;

import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class IntentFilterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_filter);

        TextView textView = (TextView) findViewById(R.id.textView2);
        Uri url = getIntent().getData();
        textView.setText(url.toString());

     }
}