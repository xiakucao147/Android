package com.example.androiddemojava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qweather.sdk.view.HeConfig;
import com.qweather.sdk.view.QWeather;


public class TeachingCaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teaching_case);
        RelativeLayout teaching_button = findViewById(R.id.teaching_button);
        RelativeLayout personalPage=findViewById(R.id.personalPage);
        RelativeLayout home_button=findViewById(R.id.homeButton);
        home_button.setOnClickListener(new View.OnClickListener() {
        TextView temperatureTextView=findViewById(R.id.temperatureTextView);
        TextView weatherTextView=findViewById(R.id.weatherTextView);

            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setClass(TeachingCaseActivity.this,HomeViewActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });
        teaching_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        personalPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setClass(TeachingCaseActivity.this,PersonnalViewActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });
    }
}