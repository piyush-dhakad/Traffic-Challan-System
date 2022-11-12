package com.nitzzz.traffic.other;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;

import com.nitzzz.traffic.R;


public class AboutUsActivity extends BaseActivity {

    public final static String TAG = "AboutUsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        start();

    }

    private void start(){

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        findViewById(R.id.txt_about).setOnClickListener(v -> {

            WebViewActivity.option = WebViewActivity.about_us;
            startActivity(new Intent(this, WebViewActivity.class));

        });

        findViewById(R.id.txt_privacy_policy).setOnClickListener(v -> {

            WebViewActivity.option = WebViewActivity.privacy_policy;
            startActivity(new Intent(this, WebViewActivity.class));

        });

        findViewById(R.id.txt_terms_of_services).setOnClickListener(v -> {

            WebViewActivity.option = WebViewActivity.terms_of_services;
            startActivity(new Intent(this, WebViewActivity.class));

        });

    }

}