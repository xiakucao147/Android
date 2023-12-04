package com.example.androiddemojava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class PersonnalViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personnal_view);
        RelativeLayout teaching_button = findViewById(R.id.teaching_button);
        RelativeLayout personalPage=findViewById(R.id.personalPage);
        RelativeLayout home_button=findViewById(R.id.homeButton);
        home_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setClass(PersonnalViewActivity.this,HomeViewActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });
        teaching_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setClass(PersonnalViewActivity.this,TeachingCaseActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });
        personalPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}