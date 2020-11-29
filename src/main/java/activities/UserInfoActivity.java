package activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hfe.R;

import org.w3c.dom.Text;

public class UserInfoActivity extends AppCompatActivity {

    //Following includes those widgets which are changeable and interactive
    private Button back;
    private Button homePage;
    private Button certification;
    private Button bind;

    private TextView hasCertificated_T;
    private TextView boundPhone_T;

    private ImageView profile_I;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        hide();

        initiateWidget();

    }

    private void initiateWidget(){
        //correspond
        back = findViewById(R.id.button_userInfo_navigationBar_back);
        homePage = findViewById(R.id.button_userInfo_list_userHomePage);
        certification = findViewById(R.id.button_userInfo_list_certification);
        bind = findViewById(R.id.button_userInfo_list_bind);

        hasCertificated_T = findViewById(R.id.text_userInfo_list_hasCertificated);
        boundPhone_T = findViewById(R.id.text_userInfo_list_bindingPhoneNumber);

        profile_I = findViewById(R.id.image_userInfo_list_homePage_ProfilePreview);
        //set action...
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        homePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAnIntentWithNoParameter(UserInfoActivity.this,HomePageActivity.class);
            }
        });
    }

    private void createAnIntentWithNoParameter(Context context , Class<?> cls){
        Intent intent = new Intent(context,cls);
        context.startActivity(intent);
    }

    public void hide(){
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null)
            actionBar.hide();
    }
}