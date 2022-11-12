package com.nitzzz.traffic.sqlite;

import android.content.Intent;
import android.widget.LinearLayout;


import com.nitzzz.traffic.R;
import com.nitzzz.traffic.other.BaseActivity;

import java.util.ArrayList;

public class SqliteTablesActivity extends BaseActivity {

    private ArrayList<Integer> tablesId = new ArrayList<>();
    private ArrayList<String> tablesName = new ArrayList<>();
    private LinearLayout linear_layout;

    @Override
    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_tables);

        start();
    }

    private void start(){

        SqliteManager database = new SqliteManager(this);

        tablesName.addAll(database.getTables());

        //set Linear Layout Dimensions
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        param.setMargins(16,16,16,16);

        linear_layout = findViewById(R.id.linear_layout);


        //Adding TextViews
        for (int i = 0; i < tablesName.size(); i++) {

            final android.widget.TextView textView = new android.widget.TextView(this);
            tablesId.add(textView.getId());
            textView.setText(tablesName.get(i));
            textView.setTextSize(18);
            textView.setLayoutParams(param);
            linear_layout.addView(textView);

            textView.setOnClickListener(new android.view.View.OnClickListener() {
                @Override
                public void onClick(android.view.View v) {

                    //do actions here...
                    Intent intent = new Intent(getBaseContext(), SqliteRecordsActivity.class);
                    intent.putExtra("table",textView.getText().toString());
                    startActivity(intent);
                }
            });

        }


    }

}