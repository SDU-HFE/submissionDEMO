package activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.hfe.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UserAccountFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 * parameters that we need:
 *      username,
 *      phoneNumber,
 *      profile,
 *      balance
 *
 * get it from database
 */
public class UserAccountFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    // TODO: Rename and change types of parameters
    //Context that fragment depends on
    private Context myContext;

    //interactive or changeable widget in fragment
    private Button setting;
    private Button mainInfo;
    private Button bill;
    private Button wallet;

    private TextView username_T;
    private TextView phoneNumber_T;
    private TextView balance_T;

    //properties : something like a map including userInformation


    public UserAccountFragment(Context context) {
        // Required empty public constructor
        myContext = context;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UserAccountFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UserAccountFragment newInstance(String param1, String param2, Context context) {
        UserAccountFragment fragment = new UserAccountFragment(context);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_user_account, container, false);
        //init
        initiateWidget(view);





        return view;
    }

    private void initiateWidget(View view){
        //correspond
        setting = view.findViewById(R.id.button_UserAccount_navigation_setting);
        mainInfo = view.findViewById(R.id.button_UserAccount_mainInfo);
        bill = view.findViewById(R.id.button_UserAccount_menu_bill);
        wallet = view.findViewById(R.id.button_UserAccount_menu_wallet);

        username_T = view.findViewById(R.id.text_UserAccount_mainInfo_username);
        phoneNumber_T = view.findViewById(R.id.text_UserAccount_mainInfo_phoneNumber);
        balance_T = view.findViewById(R.id.text_UserAccount_menu_balance);

        //set action
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAnIntentWithNoParameter(myContext,SettingActivity.class);
            }
        });
        mainInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAnIntentWithNoParameter(myContext,UserInfoActivity.class);
            }
        });
    }

    private void createAnIntentWithNoParameter(Context context , Class<?> cls){
        Intent intent = new Intent(context,cls);
        context.startActivity(intent);
    }
}