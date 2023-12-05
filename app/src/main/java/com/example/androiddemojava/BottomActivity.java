package com.example.androiddemojava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class BottomActivity extends AppCompatActivity implements View.OnClickListener {


    HomeViewFragment homeViewFragment=new HomeViewFragment();
    PersonalViewFragment personalViewFragment=new PersonalViewFragment();
    TeachCaseFragment teachCaseFragment=new TeachCaseFragment();
//    LinearLayout boxTools;
//    LinearLayout boxWeb;
//    LinearLayout boxShot;
//    LinearLayout boxSmartPay;
    View homeViewFragmentView;
    View personalViewFragmentView;
    View teachCaseFragmentView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom);


        RelativeLayout home_button=findViewById(R.id.btn1);
        RelativeLayout teaching_button = findViewById(R.id.btn2);
        RelativeLayout personalPage=findViewById(R.id.btn3);

        homeViewFragmentView=homeViewFragment.getView();
        personalViewFragmentView=personalViewFragment.getView();
        teachCaseFragmentView=teachCaseFragment.getView();


        TextView temperatureTextView=findViewById(R.id.temperatureTextView);
        TextView weatherTextView=findViewById(R.id.weatherTextView);


        teaching_button.setOnClickListener(this);
        personalPage.setOnClickListener(this);
        home_button.setOnClickListener(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.main_content, homeViewFragment).commit();//默认页面

        ShowWeather(weatherTextView,temperatureTextView);
//        boxTools.setOnClickListener(new View.OnClickListener() {
//            @Override
//        public void onClick(View v) {
//                Intent intent=new Intent();
//                intent.setClass(BottomActivity.this,ToolsBoxViewActivity.class);
//                startActivity(intent);
//            }
//        });
//        boxWeb.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                final Uri uri= Uri.parse("https://baidu.com");
//
//                Intent intent=new Intent(Intent.ACTION_VIEW,uri);
//                startActivity(intent);
//            }
//        });
//        boxSmartPay.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                launchAlipay();
//            }
//        });
//        boxShot.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent();
//                intent.setClass(BottomActivity.this,photoViewActivity.class);
//                startActivity(intent);
//            }
//        });

    }

    @Override
    public void onClick(View view) {
        int viewId = view.getId();

        if (viewId == R.id.btn1) {
            getSupportFragmentManager().beginTransaction().replace(R.id.main_content, homeViewFragment).commit();
//            boxTools = homeViewFragmentView.findViewById(R.id.boxTools);
//            boxWeb = homeViewFragmentView.findViewById(R.id.boxWeb);
//            boxShot = homeViewFragmentView.findViewById(R.id.boxShot);
//            boxSmartPay = homeViewFragmentView.findViewById(R.id.boxSmartPay);

        } else if (viewId == R.id.btn2) {

            getSupportFragmentManager().beginTransaction().replace(R.id.main_content,teachCaseFragment ).commit();
        }
        else if (viewId == R.id.btn3) {

            getSupportFragmentManager().beginTransaction().replace(R.id.main_content, personalViewFragment).commit();
        }
    }

    private void ShowWeather(TextView tv1,TextView tv2){
        Intent intent=getIntent();
        if (intent != null) {
            String tianqi = intent.getStringExtra("tianqi");
            String wendu = intent.getStringExtra("wendu");
            String fengli = intent.getStringExtra("fengli");
            String fengxiang = intent.getStringExtra("fengxiang");

            // 将数据显示在 EditText 中
            String weatherInfo = "天气：" + tianqi + "\n温度：" + wendu ;

            tv1.setText(weatherInfo);
            weatherInfo= "风力：" + fengli + "\n风向：" + fengxiang;
            tv2.setText(weatherInfo);
        }
    }
//    private void launchAlipay() {
//        try {
//            // 使用支付宝的包名和活动名创建 Intent
//            Intent intent = new Intent(Intent.ACTION_VIEW);
//            intent.setData(Uri.parse("alipays://platformapi/startApp"));
//            startActivity(intent);
//        } catch (ActivityNotFoundException e) {
//            // 如果支付宝未安装，可以在这里处理
//            Toast.makeText(this, "支付宝未安装", Toast.LENGTH_SHORT).show();
//        }
//    }

}