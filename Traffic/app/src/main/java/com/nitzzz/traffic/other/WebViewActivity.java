package com.nitzzz.traffic.other;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ProgressBar;

import androidx.appcompat.widget.Toolbar;

import com.nitzzz.traffic.R;

public class WebViewActivity extends BaseActivity {

    public static final String TAG = "WebViewActivity";

    public static final int terms_of_services = 1;
    public static final int privacy_policy = 2;
    public static final int about_us = 3;

    public static int option = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        try{
            AsyncTask.execute(() -> runOnUiThread(() -> start()));
        }catch (Exception ex){}

    }

    private void start(){

        String title = "", path = "";

        switch (option){

            case terms_of_services :

                title = "Terms and Conditions";
                path = "file:///android_res/raw/terms_and_conditions.html";
                break;

            case privacy_policy :

                title = "Terms and Conditions";
                path = "file:///android_res/raw/terms_and_conditions.html";
                break;

            case about_us :

                title = "About Us";
                path = "file:///android_res/raw/about.html";
                break;

        }


        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
        toolbar.setTitle(title);

        ProgressBar progressBar = findViewById(R.id.progress_bar);
        WebView webView = findViewById(R.id.web_view);

        webView.loadUrl(path);

        progressBar.setVisibility(View.GONE);
        webView.setVisibility(View.VISIBLE);

    }
}