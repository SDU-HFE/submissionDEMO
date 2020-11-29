package activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.hfe.R;

public class Home extends AppCompatActivity implements View.OnClickListener {
private Button home;
private Button personal;
    Context myContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        replaceFragment(new HomeFragment(Home.this));
        home=findViewById(R.id.button_home);
        personal=findViewById(R.id.button_personal);
        home.setBackgroundResource(R.drawable.home_clickeed);
        home.setOnClickListener(this);
        personal.setOnClickListener(this);

    }
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.Home_fragment,fragment);
        transaction.commit();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_home:
                home.setBackgroundResource(R.drawable.home_clickeed);
                personal.setBackgroundResource(R.drawable.personal);
                replaceFragment(new HomeFragment(Home.this));
                break;
            default:
                home.setBackgroundResource(R.drawable.home);
                personal.setBackgroundResource(R.drawable.personal_clicked);
                replaceFragment(new UserAccountFragment(Home.this));
                break;
        }
    }
}