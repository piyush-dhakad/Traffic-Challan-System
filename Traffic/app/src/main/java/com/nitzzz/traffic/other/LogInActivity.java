package com.nitzzz.traffic.other;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.nitzzz.traffic.R;
import com.nitzzz.traffic.home.HomeActivity;
import com.nitzzz.traffic.models.RuleModel;
import com.nitzzz.traffic.models.UserModel;
import com.nitzzz.traffic.sqlite.Config;
import com.nitzzz.traffic.utility.FileUtil;
import com.nitzzz.traffic.utility.Msg;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LogInActivity extends BaseActivity {

    @BindView(R.id.txt_user_name) EditText txt_user_name;
    @BindView(R.id.txt_password) EditText txt_password;
    @BindView(R.id.txt_log_in) TextView txt_log_in;

    List<UserModel> dataset = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        ButterKnife.bind(this);

        start();

    }

    private void start(){

        txt_log_in.setOnClickListener(v -> {

            if(txt_user_name.getText().toString().isEmpty()){

                txt_user_name.requestFocus();
                Msg.error(this, "Username required.");
                return;
            }

            if(txt_password.getText().toString().isEmpty()){

                txt_password.requestFocus();
                Msg.error(this, "Password required.");
                return;
            }

            if(load_user_file()){

                userModel = null;

                String user = txt_user_name.getText().toString().trim();
                String pass = txt_password.getText().toString().trim();

                for(UserModel model  : dataset){

                    if(user.equals(model.username) && user.equals(model.password)){

                        userModel = model;
                    }
                }

                if(userModel == null){

                    Msg.error(this, "Wrong username and password!");
                    return;
                }

                startActivity(new Intent(this, HomeActivity.class));
                finishAffinity();
            }

        });

    }

    private boolean load_user_file(){

        try{

            String str = FileUtil.get_assets(this, Config.user_file);

            if(str != null){

                Type listOfTestObject = new TypeToken<List<UserModel>>(){}.getType();
                List<UserModel> users = gson.fromJson(str, listOfTestObject);
                this.dataset.clear();
                this.dataset.addAll(users);
                return true;
            }

            return false;

        }catch (Exception ex){

            Msg.error(this, "User Not Found!");
            return false;
        }
    }

}