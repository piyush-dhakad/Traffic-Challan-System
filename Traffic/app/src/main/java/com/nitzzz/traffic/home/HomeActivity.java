package com.nitzzz.traffic.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.nitzzz.traffic.R;
import com.nitzzz.traffic.other.AboutUsActivity;
import com.nitzzz.traffic.other.BaseActivity;
import com.nitzzz.traffic.other.LogInActivity;
import com.nitzzz.traffic.sqlite.SqliteTablesActivity;

public class HomeActivity extends BaseActivity {

    public static final String TAG = "HomeActivity";

    private Toolbar toolbar;
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        drawer_setup();
        start();

    }

    private void start(){

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout,new HomeFragment(), HomeFragment.TAG)
                .commit();

        header_title.setOnClickListener(v->{

            startActivity(new Intent(this, SqliteTablesActivity.class));
            drawer_close();
        });

    }


    /*============== drawer ==============*/

    TextView header_title;
    ImageView imgProfile;

    private void drawer_setup() {

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        toolbar.setOverflowIcon(getDrawable(R.drawable.icon_more_vertical));

        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.getDrawerArrowDrawable().setColor(getColor(R.color.White));
        toggle.syncState();

        toolbar.setNavigationIcon(getDrawable(R.drawable.toggle));

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(menuItem -> {

            drawer.closeDrawer(GravityCompat.START);

            return true;
        });

        /*================ drawer and toolbar ================*/

        header_title = findViewById(R.id.header_title);
        imgProfile = findViewById(R.id.header_image);

        header_title.setText(userModel.full_name);

        findViewById(R.id.nav_home).setOnClickListener(v -> {


        });


        findViewById(R.id.nav_about).setOnClickListener(v -> {

            startActivity(new Intent(this, AboutUsActivity.class));
            drawer_close();
        });

        findViewById(R.id.nav_logout).setOnClickListener(v -> {

            drawer_close();

            AlertDialog alertDialog = new AlertDialog.Builder(this)
                    .setTitle("Log Out")
                    .setMessage("Are you sure ?")
                    .setNegativeButton("No", (dialog, which) -> dialog.dismiss())
                    .setPositiveButton("Yes", (dialog, which) -> {
                        dialog.dismiss();

                        startActivity(new Intent(this, LogInActivity.class));
                        finishAffinity();

                    })
                    .create();

            alertDialog.show();

        });


    }

    private void drawer_close(){

        if (drawer.isDrawerOpen(Gravity.LEFT))
            drawer.closeDrawer(Gravity.LEFT);

    }

    /*============== drawer ==============*/

    @Override
    public void onBackPressed() {

        if (drawer.isDrawerOpen(Gravity.LEFT)) {
            drawer.closeDrawer(Gravity.LEFT);
            return;
        }

        super.onBackPressed();

    }

    @Override
    protected void onResume() {
        super.onResume();



    }


}