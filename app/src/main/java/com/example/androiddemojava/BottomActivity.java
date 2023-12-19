package com.example.androiddemojava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
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

    View homeViewFragmentView;
    View personalViewFragmentView;
    View teachCaseFragmentView;
    RelativeLayout home_button;
    RelativeLayout teaching_button ;
    RelativeLayout personalPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom);


        home_button=findViewById(R.id.btn1);
        teaching_button = findViewById(R.id.btn2);
        personalPage=findViewById(R.id.btn3);

        homeViewFragmentView=homeViewFragment.getView();
        personalViewFragmentView=personalViewFragment.getView();
        teachCaseFragmentView=teachCaseFragment.getView();


        TextView temperatureTextView=findViewById(R.id.temperatureTextView);
        TextView weatherTextView=findViewById(R.id.weatherTextView);


        teaching_button.setOnClickListener(this);
        personalPage.setOnClickListener(this);
        home_button.setOnClickListener(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.main_content, homeViewFragment).commit();//默认页面
        home_button.setBackgroundColor(Color.parseColor("#ffffff"));

        ShowWeather(weatherTextView,temperatureTextView);

    }

    @Override
    public void onClick(View view) {
        int viewId = view.getId();

        if (viewId == R.id.btn1) {
            getSupportFragmentManager().beginTransaction().replace(R.id.main_content, homeViewFragment).commit();
            home_button.setBackgroundColor(Color.parseColor("#ffffff"));
            teaching_button.setBackgroundColor(Color.parseColor("#bae3eb"));
            personalPage.setBackgroundColor(Color.parseColor("#bae3eb"));
        } else if (viewId == R.id.btn2) {

            getSupportFragmentManager().beginTransaction().replace(R.id.main_content,teachCaseFragment ).commit();
            home_button.setBackgroundColor(Color.parseColor("#bae3eb"));
            teaching_button.setBackgroundColor(Color.parseColor("#ffffff"));
            personalPage.setBackgroundColor(Color.parseColor("#bae3eb"));
        }
        else if (viewId == R.id.btn3) {

            getSupportFragmentManager().beginTransaction().replace(R.id.main_content, personalViewFragment).commit();
            home_button.setBackgroundColor(Color.parseColor("#bae3eb"));
            teaching_button.setBackgroundColor(Color.parseColor("#bae3eb"));
            personalPage.setBackgroundColor(Color.parseColor("#ffffff"));
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