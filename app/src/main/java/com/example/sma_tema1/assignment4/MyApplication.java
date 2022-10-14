package com.example.sma_tema1.assignment4;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;

public class MyApplication extends Application {
    private  static  MyApplication singleton;

    private Bitmap bitmap;

    public MyApplication getInstance()
    {
        return singleton;
    }

    @Override
    public void onCreate()
    {
        super.onCreate();
        singleton = this;
    }

    public Bitmap getBitmap()
    {
        return bitmap;
    }

    public void  setBitmap(Bitmap bmp)
    {
        this.bitmap = bitmap;
    }

    public Context getApplicationContext() {
        return  this.getApplicationContext();
    }

}
