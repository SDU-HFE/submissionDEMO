package activities;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hfe.R;

import register.Register;


public class MainActivity extends AppCompatActivity {

    private Button mlogin;
    private Button mnewaccount;
    private Button mfindpassword;
    private EditText accountEdit;
    private EditText passwordEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        accountEdit = findViewById(R.id.login_account);
        passwordEdit = findViewById(R.id.login_password);


        mlogin = findViewById(R.id.button_login);
        mlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                        Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                        Intent intent1 = new Intent(MainActivity.this, Home.class);
                        startActivity(intent1);
                        finish();

            }
        });
//        注册
        mnewaccount = findViewById(R.id.button_login_newaccount);
        mnewaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "热烈欢迎萌新！！！！", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, Register.class);
                startActivity(intent);
            }
        });




        //              从本地数据库填入数据
//        dateBaseHelper = new DateBaseHelper(this, "AccountData", null, 1);
//        dateBaseHelper.getWritableDatabase();
//        Cursor cursor = dateBaseHelper.getWritableDatabase().query("AccountData", null, null, null, null, null, null);
//        String Account = null, password = null;
//        while (cursor.moveToNext()) {
//            Account = cursor.getString(cursor.getColumnIndex("Account"));
//            password = cursor.getString(cursor.getColumnIndex("password"));
//        }
//        dateBaseHelper.getWritableDatabase().close();
//        accountEdit.setText(Account);
//        passwordEdit.setText(password);

//        如果有数据，直接执行本地登录
//        if (Account!=null&&password!=null&&Account!=""&&password!="") {
//            HttpUtil.sendHttpRequest("http://120.24.33.180:8080/login?user=" + accountEdit.getText().toString()+ "&&password=" + passwordEdit.getText().toString());
//            if ("登录成功".equals(HttpUtil.msg)) {
//                Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
//                Intent intent1 = new Intent(MainActivity.this, MainUI.class);
//                startActivity(intent1);
//                finish();
//            }
//        }
//      从接口获取数据
//        mlogin = findViewById(R.id.login);
//        mlogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AccountNow = accountEdit.getText().toString();
//                passwordNow = passwordEdit.getText().toString();
//
//                if (accountEdit.getText() == null || accountEdit.getText().length() == 0) {
//                    accountEdit.setError("用户名不能为空");
//                } else if (passwordEdit.getText() == null || passwordEdit.getText().length() == 0) {
//                    passwordEdit.setError("密码不能为空");
//                } else {
//                    HttpUtil.sendHttpRequest("http://120.24.33.180:8080/login?user=" + AccountNow + "&&password=" + passwordNow);
//
//                    if ("用户不存在".equals(HttpUtil.msg)) {
//                        Toast.makeText(MainActivity.this, "用户不存在", Toast.LENGTH_SHORT).show();
//                    } else if ("密码错误".equals(HttpUtil.msg)) {
//                        Toast.makeText(MainActivity.this, "密码错误", Toast.LENGTH_SHORT).show();
//                    } else {
//                        ContentValues values = new ContentValues();
//                        values.put("Account", accountEdit.getText().toString());
//                        values.put("password", passwordEdit.getText().toString());
//                        Log.d("MainActivity", "Account:" + accountEdit.getText().toString());
//                        Log.d("MainActivity", "password:" + passwordEdit.getText().toString());
//                        dateBaseHelper.getWritableDatabase().insert("AccountData", null, values);
//                        dateBaseHelper.getWritableDatabase().close();
//
//                        Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
//                        Intent intent1 = new Intent(MainActivity.this, mainInterface.class);
//                        startActivity(intent1);
//                        finish();
//                    }
//                }
//            }
//        });

        //注册
//        mnewaccount = findViewById(R.id.login_newaccount);
//        mnewaccount.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, "热烈欢迎萌新！！！！", Toast.LENGTH_SHORT).show();
//                Intent intent2 = new Intent(MainActivity.this, NewAccount.class);
//                startActivity(intent2);
//            }
//        });

        //找回密码
//        mfindpassword = findViewById(R.id.login_findpassword);
//        mfindpassword.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent3 = new Intent(MainActivity.this, RetakePassword.class);
//                startActivity(intent3);
//            }
//        });
    }
    public static String AccountNow;
    public static String passwordNow;
}


