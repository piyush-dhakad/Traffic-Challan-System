package com.nitzzz.traffic.other;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.nitzzz.traffic.R;
import com.nitzzz.traffic.sqlite.Config;

import java.io.File;

public class SplashActivity extends BaseActivity {

    public static final String[] PERMISSIONS = {
            Manifest.permission.INTERNET,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_WIFI_STATE
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(() -> start(), 2000);

    }


    private void start(){

//        PermissionDialogs permissionDialogs = new PermissionDialogs();
//        permissionDialogs.isPermissionGrantedRestricted(this, PERMISSIONS, () -> {
//
//            create_directory();

            startActivity(new Intent(this, LogInActivity.class));
            finishAffinity();

//        });

    }


    private void create_directory(){

        try {

            String app_directory = Config.app_directory;
            String config_directory =  Config.config_directory;

            File dir = new File(app_directory);

            //create app directory if not exist
            if(!dir.exists())
                dir.mkdir();

            //invoice directory
            dir = new File(config_directory);

            //create database directory if not exist
            if(!dir.exists())
                dir.mkdir();


            Log.e(getClass().getSimpleName(), "directory created success!");

        }catch (Exception ex){

            ex.printStackTrace();
            Log.e(getClass().getSimpleName(), "directory created failed!");
        }
    }

}