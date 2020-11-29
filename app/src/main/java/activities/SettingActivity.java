package activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.hfe.R;

public class SettingActivity extends AppCompatActivity {

    //Following includes those widgets which are changeable and interactive
    private Button back;                    //返回
    private Button acc_security;            //账户与安全
    private Button infoRemind;              //消息通知
    private Button common;                  //通用
    private Button secret;                  //隐私
    private Button changeAcc;               //更换账号
    private Button logout;                  //登出

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        hide();

        //corresponding
        initiateButton();





    }

    private void initiateButton(){
        //correspond button
        back = findViewById(R.id.button_setting_navigationBar_back);
        acc_security = findViewById(R.id.button_setting_list_accountSecurity);
        infoRemind = findViewById(R.id.button_setting_list_messageRemind);
        common = findViewById(R.id.button_setting_list_common);
        secret = findViewById(R.id.button_setting_list_secret);
        changeAcc = findViewById(R.id.button_setting_list_changeAccount);
        logout = findViewById(R.id.button_setting_list_logout);

        //set action
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logoutCurrentAccount();
            }
        });
    }

    private void logoutCurrentAccount(){
        //insert implementation here...

    }

    public void hide(){
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null)
            actionBar.hide();
    }


}