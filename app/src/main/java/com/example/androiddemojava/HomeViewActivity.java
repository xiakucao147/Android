package com.example.androiddemojava;

import androidx.appcompat.app.AppCompatActivity;

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

public class HomeViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_view);
        LinearLayout boxTools = findViewById(R.id.boxTools);
        LinearLayout boxWeb=findViewById(R.id.boxWeb);
        LinearLayout boxShot=findViewById(R.id.boxShot);
        LinearLayout boxSmartPay = findViewById(R.id.boxSmartPay);
        RelativeLayout teaching_button = findViewById(R.id.teaching_button);
        RelativeLayout personalPage=findViewById(R.id.personalPage);
        RelativeLayout home_button=findViewById(R.id.homeButton);

        TextView temperatureTextView=findViewById(R.id.temperatureTextView);
        TextView weatherTextView=findViewById(R.id.weatherTextView);
        ShowWeather(weatherTextView,temperatureTextView);



        home_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        teaching_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setClass(HomeViewActivity.this,TeachingCaseActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });
        personalPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setClass(HomeViewActivity.this,PersonnalViewActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });
        boxTools.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(HomeViewActivity.this,ToolsBoxViewActivity.class);
                startActivity(intent);
            }
        });
        boxWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Uri uri= Uri.parse("https://baidu.com");

                Intent intent=new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);
            }
        });
        boxSmartPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchAlipay();
            }
        });
        boxShot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(HomeViewActivity.this,photoViewActivity.class);
                startActivity(intent);
            }
        });
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
    private void launchAlipay() {
        try {
            // 使用支付宝的包名和活动名创建 Intent
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("alipays://platformapi/startApp"));
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            // 如果支付宝未安装，可以在这里处理
            Toast.makeText(this, "支付宝未安装", Toast.LENGTH_SHORT).show();
        }
    }
}
