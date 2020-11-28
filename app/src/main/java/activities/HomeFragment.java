package activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.hfe.R;

public class HomeFragment extends Fragment {
    private View view;
    private Double mbalance=100.0;
    static boolean certificated;
    private ImageButton lifePay;
    Context myContext;

    public HomeFragment(Context context){
        myContext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        view=inflater.inflate(R.layout.home_fragment,container,false);

        //显示在扫一扫底部的提示性文本
        TextView tipBelowScan=view.findViewById(R.id.Home_tipBelowScan);
        if (certificated){
            String str="扫一扫支付";
            tipBelowScan.setText(str);

        }else {
            String str = "请先实名认证";
            tipBelowScan.setText(str);
        }

        //主界面显示余额
        TextView balance=view.findViewById(R.id.Home_balanceText);
        String str="余额为："+mbalance;
        balance.setText(str);

        //跳转到生活缴费
        lifePay=view.findViewById(R.id.home_lifepay);
        lifePay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(myContext,LifePay.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
