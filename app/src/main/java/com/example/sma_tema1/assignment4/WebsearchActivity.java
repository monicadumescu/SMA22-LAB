package com.example.sma_tema1.assignment4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.example.sma_tema1.R;

public class WebsearchActivity extends AppCompatActivity {

    public static final String EXTRA_URL = "https://www.google.com/search?q=cat&tbm=isch&source=lnms&sa=X";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_websearch);
        WebView myWebView = (WebView) findViewById(R.id.webView);

        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        myWebView.setWebViewClient(new MyCustomWebViewClient());
        myWebView.loadUrl("https://www.google.com/search?q=cat&tbm=isch&source=lnms&sa=X");

        ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData abs = clipboardManager.getPrimaryClip();
        ClipData.Item item = abs.getItemAt(0);
        String url = item.getText().toString();

        if(!url.contains("https://google/images/")) {
            Toast.makeText(this, "URL not valid. Try another.", Toast.LENGTH_SHORT).show();
        }
        else
        {
            if (myWebView.getId() == R.id.loadBackground)
            {
                Intent intent = new Intent(this, ImageIntentService.class);
                intent.putExtra(EXTRA_URL, url);
                startService(intent);
            }
            else
            {
                Intent intent = new Intent(this, ForegroundImageService.class);
                intent.setAction(ForegroundImageService.STARTFOREGROUND_ACTION);
                intent.putExtra(EXTRA_URL, url);
                startService(intent);
            }

        }
    }
}