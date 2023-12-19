package com.example.androiddemojava;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class PersonalViewFragment extends Fragment implements View.OnClickListener{


    TextView myPage;
    TextView callMe;
    public PersonalViewFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView=inflater.inflate(R.layout.fragment_personal_view, container, false);
        myPage=fragmentView.findViewById(R.id.myPage);
        callMe=fragmentView.findViewById(R.id.callMe);

        myPage.setOnClickListener(this);
        callMe.setOnClickListener(this);

        return  fragmentView;
    }

    @Override
    public void onClick(View view) {
    int viewId=view.getId();
    if(viewId==R.id.myPage){

        final Uri uri= Uri.parse("https://www.cqjtu.edu.cn");

        Intent intent=new Intent(Intent.ACTION_VIEW,uri);
        startActivity(intent);
    }
    else if(viewId==R.id.callMe)
    {
        if(shouldAskPermissions()){
            askPermissions();
        }

        dialPhoneNumber(callMe.getText().toString()); // 替换为要拨打的实际电话号码
    }
    }
    private void dialPhoneNumber(String phoneNumber) {
        // 创建一个拨打电话的 Intent
        Intent dialIntent = new Intent(Intent.ACTION_DIAL);
        dialIntent.setData(Uri.parse("tel:"+phoneNumber));
        startActivity(dialIntent);

    }
    private void askPermissions(){
        String[] permissions = {
                "android.permission.CALL_PHONE"
        };
        int requestCode = 200;
        requestPermissions(permissions,requestCode);
    }
    protected boolean shouldAskPermissions(){
        return (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1);//判断版本号
    }
}