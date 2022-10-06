package com.example.sma_tema1.lifecycle;

import androidx.appcompat.app.AppCompatActivity;
import com.example.sma_tema1.R;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.view.View;

public class ActivityA extends AppCompatActivity {

    private static final String TAG = "ActivityA";

    public void clicked(View view)
    {
        switch (view.getId())
        {
            case R.id.buttonA:
                startActivity(new Intent(this,ActivityA.class));
                break;
            case R.id.buttonb:
                startActivity(new Intent(this, ActivityB.class));
                break;
            case R.id.buttonc:
                startActivity(new Intent(this, ActivityC.class));
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity);
        setTitle("A");
        Log.d(TAG, "onCreate A");
        Log.d(TAG, "onStart A");
        Log.d(TAG, "onResume A");
        Log.d(TAG, "onRestart A");
        Log.d(TAG, "onPause A");
        Log.d(TAG, "onStop A");
        Log.d(TAG, "onDestroy A");
    }

    @Override
    protected  void onStart()
    {
        super.onStart();
        Log.d(ActivityA.TAG, "onStart A");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(ActivityA.TAG, "onResume A");
    }

    @Override
    protected void onRestart()
    {
        super.onRestart();
        Log.d(ActivityA.TAG, "onRestart A");
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        Log.d(ActivityA.TAG, "onPause A");
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        Log.d(ActivityA.TAG, "onStop A");
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        Log.d(ActivityA.TAG, "onDestroy A");
    }
}