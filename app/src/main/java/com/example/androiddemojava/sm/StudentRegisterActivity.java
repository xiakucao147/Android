package com.example.androiddemojava.sm;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androiddemojava.R;
import com.example.androiddemojava.photoViewActivity;

import java.util.Calendar;

public class StudentRegisterActivity extends AppCompatActivity implements View.OnClickListener{

    EditText stuData;
    Calendar calendar;

    Button btn_sel_course,btn_admin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_register);
        stuData=findViewById(R.id.stu_date);
        btn_admin=findViewById(R.id.btn_admin);
        btn_sel_course=findViewById(R.id.btn_sel_course);

        stuData.setOnClickListener(this);
        btn_sel_course.setOnClickListener(this);
        btn_admin.setOnClickListener(this);

        calendar = Calendar.getInstance();

        // 调用函数显示日期选择器


    }
    public static void showDatePickerDialog(Activity activity, int themeResId, final TextView tv, Calendar calendar) {
        // 直接创建一个DatePickerDialog对话框实例，并将它显示出来
        new DatePickerDialog(activity , themeResId,new DatePickerDialog.OnDateSetListener() {
            // 绑定监听器(How the parent is notified that the date is set.)
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // 此处得到选择的时间，可以进行你想要的操作
                tv.setText("您选择了：" + year + "年" + (monthOfYear+1)+ "月" + dayOfMonth + "日");

            }
        }
                // 设置初始日期
                , calendar.get(Calendar.YEAR)
                ,calendar.get(Calendar.MONTH)
                ,calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    @Override
    public void onClick(View view) {
        int viewId=view.getId();
        if(viewId == R.id.stu_date){
            Toast.makeText(StudentRegisterActivity.this, "点击", Toast.LENGTH_SHORT).show();
            showDatePickerDialog(this, androidx.appcompat.R.style.Theme_AppCompat, stuData, calendar);
        }
        else if(viewId==R.id.btn_admin){
            Intent intent=new Intent();
            intent.setClass(StudentRegisterActivity.this, StudentManageActivity.class);
            startActivity(intent);
        }
        if(viewId == R.id.btn_sel_course) {
            Intent intent=new Intent();
            intent.setClass(StudentRegisterActivity.this, StudentSelectActivity.class);
            startActivity(intent);
        }
    }
}