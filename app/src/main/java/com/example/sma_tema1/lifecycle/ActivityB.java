package com.example.sma_tema1.lifecycle;

import androidx.appcompat.app.AppCompatActivity;
import com.example.sma_tema1.R;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class ActivityB extends AppCompatActivity {
    private static final String TAG = "ActivityB";

    public void clicked(View view)
    {
        switch (view.getId())
        {
            case R.id.button_a:
                startActivity(new Intent(this,ActivityA.class));
                break;
            case R.id.button_b:
                startActivity(new Intent(this, ActivityB.class));
                break;
            case R.id.button_c:
                startActivity(new Intent(this, ActivityC.class));
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);
        setTitle("B");
        Log.d(TAG, "onCreate B");
        Log.d(TAG, "onStart B");
        Log.d(TAG, "onResume B");
        Log.d(TAG, "onRestart B");
        Log.d(TAG, "onPause B");
        Log.d(TAG, "onStop B");
        Log.d(TAG, "onDestroy B");
    }

    @Override
    protected  void onStart()
    {
        super.onStart();
        Log.d(ActivityB.TAG, "onStart B");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(ActivityB.TAG, "onResume B");
    }

    @Override
    protected void onRestart()
    {
        super.onRestart();
        Log.d(ActivityB.TAG, "onRestart B");
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        Log.d(ActivityB.TAG, "onPause B");
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        Log.d(ActivityB.TAG, "onStop B");
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        Log.d(ActivityB.TAG, "onDestroy B");
    }
}