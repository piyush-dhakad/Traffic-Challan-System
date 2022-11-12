package com.nitzzz.traffic.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;

import com.nitzzz.traffic.R;
import com.nitzzz.traffic.other.BaseActivity;

public class ReceiptAddActivity extends BaseActivity {

    public static boolean veh_no = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt_add);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        findViewById(R.id.vehicle_number).setOnClickListener(v->{

            veh_no = true;
            startActivity(new Intent(this, ReciptAdd1Activity.class));
        });

        findViewById(R.id.driving_license).setOnClickListener(v->{

            veh_no = false;
            startActivity(new Intent(this, ReciptAdd1Activity.class));
        });

    }
}