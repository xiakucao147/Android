package com.example.androiddemojava;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class InterfaceDesignActivity extends AppCompatActivity implements View.OnClickListener {

    CalFragment calFragment=new CalFragment();
    Fragment1 fragment1=new Fragment1();
    Fragment2 fragment2=new Fragment2();

    View calFragmentView;
    View fragment1View;
    View fragment2View;

    RelativeLayout btncal;
    RelativeLayout btnFL ;
    RelativeLayout btnRL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interface_design);
        btncal=findViewById(R.id.btn_cal);
        btnFL = findViewById(R.id.btn_FL);
        btnRL=findViewById(R.id.btn_RL);

        calFragmentView=calFragment.getView();
        fragment1View=fragment1.getView();
        fragment2View=fragment2.getView();


        btncal.setOnClickListener(this);
        btnFL.setOnClickListener(this);
        btnRL.setOnClickListener(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.main_content, calFragment).commit();
        btncal.setBackgroundColor(Color.parseColor("#ffffff"));
        btnFL.setBackgroundColor(Color.parseColor("#bae3eb"));
        btnRL.setBackgroundColor(Color.parseColor("#bae3eb"));


    }

    @Override
    public void onClick(View view) {
        int viewId = view.getId();

        if (viewId == R.id.btn_cal) {
            getSupportFragmentManager().beginTransaction().replace(R.id.main_content, calFragment).commit();
            btncal.setBackgroundColor(Color.parseColor("#ffffff"));
            btnFL.setBackgroundColor(Color.parseColor("#bae3eb"));
            btnRL.setBackgroundColor(Color.parseColor("#bae3eb"));
        } else if (viewId == R.id.btn_FL) {

            getSupportFragmentManager().beginTransaction().replace(R.id.main_content,fragment1 ).commit();
            btncal.setBackgroundColor(Color.parseColor("#bae3eb"));
            btnFL.setBackgroundColor(Color.parseColor("#ffffff"));
            btnRL.setBackgroundColor(Color.parseColor("#bae3eb"));

        }
        else if (viewId == R.id.btn_RL) {

            getSupportFragmentManager().beginTransaction().replace(R.id.main_content, fragment2).commit();
            btncal.setBackgroundColor(Color.parseColor("#bae3eb"));
            btnFL.setBackgroundColor(Color.parseColor("#bae3eb"));
            btnRL.setBackgroundColor(Color.parseColor("#ffffff"));

        }
    }
}