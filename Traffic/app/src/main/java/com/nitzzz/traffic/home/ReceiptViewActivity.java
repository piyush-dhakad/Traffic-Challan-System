package com.nitzzz.traffic.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.nitzzz.traffic.R;
import com.nitzzz.traffic.models.ReceiptModel;
import com.nitzzz.traffic.models.RuleModel;
import com.nitzzz.traffic.other.BaseActivity;
import com.nitzzz.traffic.utility.NumberFormat;

import java.util.ArrayList;
import java.util.List;

public class ReceiptViewActivity extends BaseActivity {


    ReceiptModel receiptModel = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt_view);


        start();


    }

    void start(){

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());


        try{

            receiptModel = (ReceiptModel) getIntent().getSerializableExtra("model");

        }catch (Exception ex){

            toast("No extra model!");
        }



        if(receiptModel == null)
            return;

        TextView txt_name = findViewById(R.id.txt_name);
        TextView txt_mobile = findViewById(R.id.txt_mobile);
        TextView txt_date = findViewById(R.id.txt_date);
        TextView txt_amt = findViewById(R.id.txt_amt);
        TextView txt_identity1 = findViewById(R.id.txt_identity1);
        TextView txt_identity2 = findViewById(R.id.txt_identity2);

        txt_name.setText( ": " +receiptModel.full_name);
        txt_mobile.setText(": "+ NumberFormat.mobile_number_format("+91"+ receiptModel.mobile) );
        txt_date.setText( ": " +NumberFormat.date_time_format1(receiptModel.time));
        txt_amt.setText(": ₹"+ NumberFormat.to_2d( receiptModel.total_fine));

        if(receiptModel.veh_no){

            txt_identity1.setText("Vehicle No.");
            txt_identity2.setText(": " + receiptModel.vehicle_no);
        }else{

            txt_identity1.setText("Driving Lic.");
            txt_identity2.setText(": " + receiptModel.licence_no);
        }

        RecyclerView recycler_view = findViewById(R.id.recycler_view);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recycler_view.setLayoutManager(layoutManager);
        RuleListAdapter adapter = new RuleListAdapter(this, receiptModel.rule_list, (position, chk) -> {});

        adapter.read_only = true;

        recycler_view.setAdapter(adapter);
        recycler_view.setHasFixedSize(true);
        recycler_view.setNestedScrollingEnabled(false);

        adapter.notifyDataSetChanged();


    }

}