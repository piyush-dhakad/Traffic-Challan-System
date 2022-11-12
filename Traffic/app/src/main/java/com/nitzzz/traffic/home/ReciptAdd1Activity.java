package com.nitzzz.traffic.home;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.nitzzz.traffic.R;
import com.nitzzz.traffic.models.ReceiptModel;
import com.nitzzz.traffic.models.RuleModel;
import com.nitzzz.traffic.other.BaseActivity;
import com.nitzzz.traffic.sqlite.Config;
import com.nitzzz.traffic.sqlite.ReceiptModule;
import com.nitzzz.traffic.sqlite.SqliteTablesActivity;
import com.nitzzz.traffic.utility.FileUtil;
import com.nitzzz.traffic.utility.Msg;
import com.nitzzz.traffic.utility.NumberFormat;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ReciptAdd1Activity extends BaseActivity {


    List<RuleModel> dataset = new ArrayList<>();
    RuleListAdapter adapter;
    TextView txt_pay;
    double total_amt = 0;
    String full_name, mobile, veh_no, dri_lic;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipt_add1);

        start();

    }

    void start(){

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        txt_pay = findViewById(R.id.txt_pay);

        load_fine_file();

        RecyclerView recycler_view = findViewById(R.id.recycler_view);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recycler_view.setLayoutManager(layoutManager);
        adapter = new RuleListAdapter(this, dataset, (position, chk) -> {

            dataset.get(position).chk = chk;

            set_calculation();
        });

        recycler_view.setAdapter(adapter);
        recycler_view.setHasFixedSize(true);
        recycler_view.setNestedScrollingEnabled(false);

        adapter.notifyDataSetChanged();



        EditText txt_name =  findViewById(R.id.txt_name);
        EditText txt_mobile =  findViewById(R.id.txt_mobile);
        EditText txt_veh_no1 =  findViewById(R.id.txt_veh_no1);
        EditText txt_driving_lic1 =  findViewById(R.id.txt_driving_lic1);

        TextView txt_veh_no =  findViewById(R.id.txt_veh_no);
        TextView txt_driving_lic =  findViewById(R.id.txt_driving_lic);


        if(ReceiptAddActivity.veh_no){

            txt_driving_lic1.setVisibility(View.GONE);
            txt_driving_lic.setVisibility(View.GONE);
        }else{

            txt_veh_no1.setVisibility(View.GONE);
            txt_veh_no.setVisibility(View.GONE);
        }


        txt_pay.setOnClickListener(v->{

            set_calculation();

            if(ReceiptAddActivity.veh_no){

                if(txt_veh_no1.getText().toString().trim().isEmpty()){

                    txt_veh_no1.requestFocus();
                    Msg.error(this, "Vehicle No. required.");
                    return;
                }

                veh_no = txt_veh_no1.getText().toString().trim();
                dri_lic = "";

            }else{

                if(txt_driving_lic1.getText().toString().trim().isEmpty()){

                    txt_driving_lic1.requestFocus();
                    Msg.error(this, "Driving License required.");
                    return;
                }

                dri_lic = txt_driving_lic1.getText().toString().trim();
                veh_no = "";

            }


            if(txt_name.getText().toString().trim().isEmpty()){

                txt_name.requestFocus();
                Msg.error(this, "Full name required.");
                return;
            }

            if(txt_mobile.getText().toString().trim().isEmpty()){

                txt_mobile.requestFocus();
                Msg.error(this, "Mobile number required.");
                return;
            }


            if(txt_mobile.getText().toString().trim().length() < 10){

                txt_mobile.requestFocus();
                Msg.error(this, "Invalid mobile number.");
                return;
            }

            full_name = txt_name.getText().toString().trim();
            mobile = txt_mobile.getText().toString().trim();



            if(total_amt <= 0){

                Msg.error(this, "Please select at least 1 fine!");
                return;
            }

            add_new_data();

        });


        set_calculation();

    }

    private void set_calculation(){

        total_amt = 0;
        for(RuleModel ruleModel : dataset){

            if(ruleModel.chk){
                total_amt += ruleModel.fine;
            }
        }

        txt_pay.setText("PAY ₹"+ NumberFormat.to_2d(total_amt));
    }

    void add_new_data(){

        ReceiptModule receiptModule = new ReceiptModule(this);
        ReceiptModel receiptModel = new ReceiptModel();

        receiptModel.full_name = full_name;
        receiptModel.mobile = mobile;

        receiptModel.veh_no = ReceiptAddActivity.veh_no;
        receiptModel.vehicle_no = veh_no;
        receiptModel.licence_no = dri_lic;

        receiptModel.total_fine = total_amt;
        receiptModel.time = System.currentTimeMillis();

        for(RuleModel ruleModel : dataset){

            if(ruleModel.chk){
                receiptModel.rule_list.add(ruleModel);
            }
        }

        receiptModule.insert(receiptModel);


        Msg.success(this, "Data inserted successfully!");

        api_msg();


        startActivity(new Intent(this, HomeActivity.class));
        finishAffinity();
    }

    void load_fine_file(){

        try{

            String str = FileUtil.get_assets(this, Config.rule_file);

            Type listOfTestObject = new TypeToken<List<RuleModel>>(){}.getType();
            List<RuleModel> rules = gson.fromJson(str, listOfTestObject);
            dataset.addAll(rules);
        }catch (Exception ex){

            Msg.error(this, "Fine List Not Found!");
        }
    }

    void api_msg(){


    }

}