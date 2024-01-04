package com.example.androiddemojava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.androiddemojava.cameraphoto.PhotoListActivity;
import com.example.androiddemojava.sm.StudentManageActivity;
import com.example.androiddemojava.sm.StudentRegisterActivity;


public class HomeViewFragment extends Fragment implements View.OnClickListener {

    LinearLayout boxTools;
    LinearLayout boxWeb;
    LinearLayout boxShot;
    LinearLayout boxSmartPay;
    LinearLayout boxStudentManager;
    public HomeViewFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // No need to get the view here, as it may not be initialized yet

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View fragmentView = inflater.inflate(R.layout.fragment_home_view, container, false);
        // Initialize LinearLayouts
        boxTools = fragmentView.findViewById(R.id.boxTools);
        boxWeb = fragmentView.findViewById(R.id.boxWeb);
        boxShot = fragmentView.findViewById(R.id.boxShot);
        boxSmartPay = fragmentView.findViewById(R.id.boxSmartPay);
        boxStudentManager=fragmentView.findViewById(R.id.boxStudentManager);
        // Set click listeners for LinearLayouts
        boxTools.setOnClickListener(this);
        boxWeb.setOnClickListener(this);
        boxShot.setOnClickListener(this);
        boxSmartPay.setOnClickListener(this);
        boxStudentManager.setOnClickListener(this);
        return fragmentView;
    }

    @Override
    public void onClick(View view) {
        // Handle click events for each LinearLayout
        int viewId = view.getId();
        if (viewId == R.id.boxTools) {
            Log.i("Tag","TTTTTTTTTTTTT");
            Intent intent=new Intent();
            intent.setClass(getActivity(), ToolsBoxViewActivity.class);
            startActivity(intent);
            // Handle click on boxTools
        } else if (viewId == R.id.boxWeb) {
            Log.i("Tag","WWWWWWWWWWWWWWWWWW");
            final Uri uri= Uri.parse("https://baidu.com");

            Intent intent=new Intent(Intent.ACTION_VIEW,uri);
            startActivity(intent);
            // Handle click on boxWeb
        } else if (viewId == R.id.boxShot) {
            Intent intent=new Intent();
            intent.setClass(getActivity(), PhotoListActivity.class);
            startActivity(intent);
            // Handle click on boxShot
        } else if (viewId == R.id.boxSmartPay) {
            launchAlipay();
            // Handle click on boxSmartPay
        } else if (viewId == R.id.boxStudentManager) {
            Intent intent=new Intent();
            intent.setClass(getActivity(), StudentRegisterActivity.class);
            startActivity(intent);
            // Handle click on boxShot
        }
    }
    private void launchAlipay() {
        // 使用支付宝的包名和活动名创建 Intent
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("alipays://platformapi/startApp"));
        startActivity(intent);

    }

}