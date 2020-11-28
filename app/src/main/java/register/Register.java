package register;


import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hfe.R;

import HelperAndAdapter.DateBaseHelper;
import HelperAndAdapter.Http.HttpUtil;
import activities.Home;
import activities.MainActivity;


public class Register extends AppCompatActivity {
    private Button identifying_code;
    private Button mregister;
    private EditText maccount;//账号（手机号）
    private EditText mpassword;//密码
    private EditText npassword;
    private DateBaseHelper dateBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        identifying_code=findViewById(R.id.button_register_identifying_code);
        identifying_code.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(Register.this, "验证码已发送", Toast.LENGTH_SHORT).show();
            }
        });


        maccount=findViewById(R.id.edittext_register_set_account);
        mpassword=findViewById(R.id.edittext_register_set_password);

        dateBaseHelper=new DateBaseHelper(this,"AccountData",null,1);
        npassword=findViewById(R.id.edittext_register_identify_password);
        mregister=findViewById(R.id.button_register_register);
        mregister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                if (mpassword.length() < 6 || mpassword.length() > 10) {
                    Toast.makeText(Register.this, "密码不符合标准", Toast.LENGTH_SHORT).show();
                } else if (mpassword.getText().toString().equals(npassword.getText().toString()) == false) {
                    Toast.makeText(Register.this, "密码不一致，请重新输入", Toast.LENGTH_SHORT).show();
                } else {
                    HttpUtil.sendHttpRequest("http://120.24.33.180:8080/register?user=" + newAccount + "&&password=" + newpassword);
                    if ("密码不可为空".equals(HttpUtil.msg)) {
                        Toast.makeText(Register.this, "密码不可为空", Toast.LENGTH_SHORT).show();
                    } else if ("用户已存在".equals(HttpUtil.msg)) {
                        Toast.makeText(Register.this, "用户已存在", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Register.this, "注册成功", Toast.LENGTH_SHORT).show();

                        //本地数据库存储
                        dateBaseHelper.getWritableDatabase();

                        ContentValues values = new ContentValues();
                        values.put("Account", maccount.getText().toString());
                        values.put("password", mpassword.getText().toString());
                        dateBaseHelper.getWritableDatabase().insert("AccountData", null, values);
                        dateBaseHelper.getWritableDatabase().close();

                        Intent intent = new Intent(Register.this, Home.class);
                        startActivity(intent);
                    }
                    //下面是用数据库实现的代码
//                dateBaseHelper.getWritableDatabase();
//
//                Cursor cursor = dateBaseHelper.getWritableDatabase().query("AccountData", null, null, null, null, null, null);
//                String Account = null;
//                if (cursor.moveToFirst()) {
//                    while (cursor.moveToNext()) {
//                        Account = cursor.getString(cursor.getColumnIndex("Account"));
//                        if (Account.equals(acsign1.getText().toString())){
//                            break;
//                        }
//                        break;
//                    }
//                }
//                if ( Account == null) {
//                    Toast.makeText(NewAccount.this, "注册成功", Toast.LENGTH_SHORT).show();
//                    ContentValues values = new ContentValues();
//                    values.put("Account", acsign1.getText().toString());
//                    values.put("password", acsign4.getText().toString());
//                    dateBaseHelper.getWritableDatabase().insert("AccountData", null, values);
//                }else{
//                    Toast.makeText(NewAccount.this, "账号已存在", Toast.LENGTH_SHORT).show();
//                }
//                dateBaseHelper.getWritableDatabase().delete("AccountData","Account=?",new String[]{});
//                dateBaseHelper.getWritableDatabase().close();
                    finish();
                }
            }
        });
        newAccount=maccount.getText().toString();
        newpassword=mpassword.getText().toString();
    }
    public static String newAccount;
    public static String newpassword;
}
