package com.nitzzz.traffic.other;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.google.gson.Gson;
import com.nitzzz.traffic.models.UserModel;

import spencerstudios.com.bungeelib.Bungee;

public class BaseActivity extends AppCompatActivity {

    public static final Gson gson = new Gson();
    public static UserModel userModel = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bungee.slideLeft(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Bungee.slideRight(this);
    }

    static final boolean debug = true;
    public void log(String TAG, String msg){

        if(debug) Log.e(TAG, msg);
    }

    public void toast(String msg){
        if(debug) Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }


}
