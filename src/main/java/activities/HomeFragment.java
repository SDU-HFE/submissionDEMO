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
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.runtime.Permission;
import com.yzq.zxinglibrary.android.CaptureActivity;

public class HomeFragment extends Fragment {

    private static final int REQUEST_CODE_SCAN = 11;
    private static final int STORAGE_PERMISSION = 13;

    private View view;
    private Double mbalance = 0.0;
    static boolean certificated=true;
    private ImageButton lifePay;
    Context myContext;
    private ImageButton scan;

    public HomeFragment(Context context) {
        myContext = context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        view = inflater.inflate(R.layout.home_fragment, container, false);

        requestStoragePermission();

        scan = view.findViewById(R.id.Home_scan);

        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(myContext, CaptureActivity.class);
                startActivityForResult(intent, REQUEST_CODE_SCAN);
            }
        });
        //显示在扫一扫底部的提示性文本
        TextView tipBelowScan = view.findViewById(R.id.Home_tipBelowScan);
        if (certificated) {
            String str = "扫一扫支付";
            tipBelowScan.setText(str);

        } else {
            String str = "请先实名认证";
            tipBelowScan.setText(str);
        }


        //主界面显示余额
        TextView balance = view.findViewById(R.id.Home_balanceText);
        String str = "余额为：" + mbalance;
        balance.setText(str);

        //跳转到生活缴费
        lifePay = view.findViewById(R.id.home_lifepay);
        lifePay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(myContext, LifePay.class);
                startActivity(intent);
            }
        });


        return view;
    }

    private void requestStoragePermission() {
        AndPermission.with(this)
                .runtime()
                .permission(Permission.READ_EXTERNAL_STORAGE)
                .permission(Permission.CAMERA)
                .start();
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Home_scan:
                Intent intent = new Intent(myContext, LifePay.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
