package com.example.androiddemojava.sm;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androiddemojava.R;
import com.example.androiddemojava.cameraphoto.Photo;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class StudentRegisterActivity extends AppCompatActivity implements View.OnClickListener{

    public String BASEURL= null;
    final boolean[] flag = {false};
    String str=null;
    public String addURL= null;
    private OkHttpClient client;
    private JSONArray jsonArray;
    private JSONObject jsonObject;

    EditText stu_name;//学生姓名

    RadioGroup RadioGroupStuSex;//选择性别
    RadioButton radioButtonMale,radioButtonFemale;
    EditText stu_age;//学生年龄
    EditText stu_tel;//电话号码
    EditText stu_password;//密码
    EditText stu_date;//入学时间
    Spinner stu_faculty;//学院
    Spinner stu_major;//专业

    Calendar calendar;

    Button btn_sel_course,btn_admin,btn_register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_register);
        BASEURL= getString(R.string.backend_baseurl);


        stu_name        = findViewById(R.id.stu_name    );
        stu_date        = findViewById(R.id.stu_date    );
        stu_age         = findViewById(R.id.stu_age     );
        stu_tel         = findViewById(R.id.stu_tel     );
        stu_password    = findViewById(R.id.stu_password);
        stu_date        = findViewById(R.id.stu_date    );
        stu_faculty     = findViewById(R.id.stu_faculty );
        stu_major       = findViewById(R.id.stu_major   );

        btn_admin=findViewById(R.id.btn_admin);
        btn_sel_course=findViewById(R.id.btn_sel_course);
        btn_register=findViewById(R.id.btn_register);

        stu_date.setOnClickListener(this);
        btn_sel_course.setOnClickListener(this);
        btn_admin.setOnClickListener(this);
        btn_register.setOnClickListener(this);

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
                //tv.setText(year + (monthOfYear+1)+ dayOfMonth);
                     SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");

                     // 格式化日期
                     String formattedDate = sdf.format(new Date(year, monthOfYear, dayOfMonth));

                     // 将格式化后的日期设置为TextView的文本
                     tv.setText(formattedDate);


            }
        }
                // 设置初始日期
                , calendar.get(Calendar.YEAR)
                ,calendar.get(Calendar.MONTH)
                ,calendar.get(Calendar.DAY_OF_MONTH)).show();
    }
    public void studentLogin(){

    }
    public String studentRegister(Student student){

        Log.d("StudentRegister",student.toString());
        client = new OkHttpClient();
        addURL="/student/add/";
        String param = new Gson().toJson(student);

        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), param);
        Log.d("body",body.toString());
//        final Request request=new Request.Builder()
//                .url(BASEURL+addURL)
//                .post(body)
//                .build();
//        final Call call=client.newCall(request);
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                Log.d("onFailure getMessage",e.getMessage());
//            }
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//
//
//                Log.d( " response.message() ",response.message());
//                final String responseString = response.body().string();
//                System.out.println(responseString);
//                //Toast.makeText(getApplicationContext(), "注册成功", Toast.LENGTH_SHORT).show();
//
//                try {
//                    jsonObject=new JSONObject(responseString);
//                    student.setStudent_num(jsonObject.getString("num"));
//                    str=student.getStudent_num();
//                    Log.d( "setStudent_num",student.getStudent_num());
//
//
//                } catch (JSONException e) {
//                    throw new RuntimeException(e);
//                }
//
//
////                runOnUiThread(new Runnable() {
////                    @Override
////                    public void run() {
////                        try{
////                            //jsonObject=new JSONObject(responseString);
////                            student.setStudent_num(jsonObject.getString("num"));
////                            //showDialog(student,jsonObject.getString("num"));
////                            //jsonArray=jsonObject1.optJSONArray("data");
//////                            for(int i=0;i<jsonArray.length();i++){
//////                                jsonObject2=jsonArray.optJSONObject(i);
//////                                String name=jsonObject2.getString("name");
//////                                String score=jsonObject2.getString("score");
//////                                Course course=new Course(name,score);
//////                                mData.add(course);
//////                            }
////
////                        } catch (JSONException e) {
////                            throw new RuntimeException(e);
////                        }
////                    }
////                });
//
//            }
//        });


        new Thread(){
            @Override
            public void run(){
                final Request request=new Request.Builder()
                        .url(BASEURL+addURL)
                        .post(body)
                        .build();

                //Toast.makeText(getApplicationContext(), "注册成功", Toast.LENGTH_SHORT).show();

                try {
                    Call call=client.newCall(request);
                    Response response =call.execute();
                    Log.d( " response.message() ",response.message());
                    final String responseString = response.body().string();
                    System.out.println(responseString);
                    jsonObject=new JSONObject(responseString);
                    student.setStudent_num(jsonObject.getString("num"));
                    str=student.getStudent_num();
                    Log.d( "setStudent_num",student.getStudent_num());


                } catch (JSONException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }


            }
        }.start();
        return str;
    }
    public boolean showDialogAdminLogin(){
        //Log.d( "inshowDialognum","inshowDialognum");
        View studentLogin = View.inflate(StudentRegisterActivity.this,R.layout.dialog_login_student,null);
        final EditText account_stu = studentLogin.findViewById(R.id.account_stu);
        final EditText password_stu = studentLogin.findViewById(R.id.password_stu);
        AlertDialog.Builder myDialog = new AlertDialog.Builder(this);
        myDialog.setTitle("管理员登录");
        myDialog.setIcon(R.mipmap.icon_message);
        myDialog.setView(studentLogin);
        myDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                String account =account_stu.getText().toString();// {"6321"};//;
                String password = password_stu.getText().toString();//"123456";//;
                User user = new User();
                user.setAccount(account);
                user.setPassword(password);
                client = new OkHttpClient();
                addURL="/user/login/";
                String param = new Gson().toJson(user);

                RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), param);
                Log.d("body",body.toString());
                new Thread(){
                    @Override
                    public void run(){
                        final Request request=new Request.Builder()
                                .url(BASEURL+addURL)
                                .post(body)
                                .build();

                        //Toast.makeText(getApplicationContext(), "注册成功", Toast.LENGTH_SHORT).show();

                        try {
                            Call call=client.newCall(request);
                            Response response =call.execute();
                            Log.d( " response.message() ",response.message());
                            final String responseString = response.body().string();
                            System.out.println(responseString);
                            jsonObject=new JSONObject(responseString);
                            if(jsonObject.getString("error_message").equals("access")) flag[0] =true ;
                            //student.setStudent_num(jsonObject.getString("error_message"));
                            // str=student.getStudent_num();
                            //Log.d( "erro",student.getStudent_num());

                            if(flag[0])
                            {
                                flag[0]=false;
                                //Toast.makeText(getApplicationContext(), "成功", Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent();
                                intent.setClass(StudentRegisterActivity.this, StudentManageActivity.class);
                                startActivity(intent);
                            }
                            else
                            {
                                //Toast.makeText(getApplicationContext(), "失败", Toast.LENGTH_SHORT).show();

                            }
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }


                    }
                }.start();


            }
        });
        myDialog.setNegativeButton("取消", null);
        myDialog.show();
        return flag[0];
    }
    public boolean showDialogStudentLogin(){

        //Log.d( "inshowDialognum","inshowDialognum");
        View studentLogin = View.inflate(StudentRegisterActivity.this,R.layout.dialog_login_student,null);
        final EditText account_stu = studentLogin.findViewById(R.id.account_stu);
        final EditText password_stu = studentLogin.findViewById(R.id.password_stu);
        AlertDialog.Builder myDialog = new AlertDialog.Builder(this);
        myDialog.setTitle("学生登录选课系统");
        myDialog.setIcon(R.mipmap.icon_message);
        myDialog.setView(studentLogin);
        myDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                String account =account_stu.getText().toString();// {"6321"};//;
                String password = password_stu.getText().toString();//"123456";//;
                Student student = new Student();
                student.setStudent_num(account);
                student.setPassword(password);
                client = new OkHttpClient();
                addURL="/student/login/";
                String param = new Gson().toJson(student);

                RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), param);
                Log.d("body",body.toString());
                new Thread(){
                    @Override
                    public void run(){
                        final Request request=new Request.Builder()
                                .url(BASEURL+addURL)
                                .post(body)
                                .build();

                        //Toast.makeText(getApplicationContext(), "注册成功", Toast.LENGTH_SHORT).show();

                        try {
                            Call call=client.newCall(request);
                            Response response =call.execute();
                            Log.d( " response.message() ",response.message());
                            final String responseString = response.body().string();
                            System.out.println(responseString);
                            jsonObject=new JSONObject(responseString);
                            if(jsonObject.getString("error_message").equals("access")) flag[0] =true ;
                            //student.setStudent_num(jsonObject.getString("error_message"));
                           // str=student.getStudent_num();
                            //Log.d( "erro",student.getStudent_num());

                            if(flag[0])
                            {
                                flag[0]=false;
                                //Toast.makeText(getApplicationContext(), "成功", Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent();
                                intent.setClass(StudentRegisterActivity.this, StudentSelectActivity.class);
                                intent.putExtra("student_num",account);
                                startActivity(intent);
                            }
                            else
                            {
                                //Toast.makeText(getApplicationContext(), "失败", Toast.LENGTH_SHORT).show();

                            }
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }


                    }
                }.start();


            }
        });
        myDialog.setNegativeButton("取消", null);
        myDialog.show();
        return flag[0];
    }
    public void showDialog(Student student){
        Log.d( "inshowDialognum","inshowDialognum");
        View show2Student = View.inflate(StudentRegisterActivity.this,R.layout.dialog_hint2student,null);
        //final TextView stu_name = show2Student.findViewById(R.id.stu_name);
        final TextView stu_num = show2Student.findViewById(R.id.stu_num);
        //stu_name.setText(str);
        stu_num.setText(str);
        AlertDialog.Builder myDialog = new AlertDialog.Builder(this);
        myDialog.setTitle("学号");
        myDialog.setIcon(R.mipmap.icon_message);
        myDialog.setView(show2Student);
        myDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //ImageFileAccess imageFileAccess=new ImageFileAccess(PhotoListActivity.this);

            }
        });
       // myDialog.setNegativeButton("取消", null);
        myDialog.show();
    }
    @Override
    public void onClick(View view) {
        int viewId=view.getId();
        if(viewId == R.id.stu_date){
            Toast.makeText(StudentRegisterActivity.this, "点击", Toast.LENGTH_SHORT).show();
            showDatePickerDialog(this, androidx.appcompat.R.style.Theme_AppCompat, stu_date, calendar);
        }
        else if(viewId==R.id.btn_admin){
            showDialogAdminLogin();

        }
        if(viewId == R.id.btn_sel_course) {
            showDialogStudentLogin();



        }
        if(viewId == R.id.btn_register){
            String stuName = stu_name.getText().toString().trim();
            String stuAge = stu_age.getText().toString().trim();
            
            String stuTel = stu_tel.getText().toString().trim();
            String stuPassword = stu_password.getText().toString().trim();
            String stuDate = stu_date.getText().toString().trim();
            String stuMajor = stu_major.getSelectedItem().toString();
            String stuFaculty = stu_faculty.getSelectedItem().toString();
        // 检查是否为空并给出Toast提示
            if (stuName.isEmpty()) {
                Toast.makeText(getApplicationContext(), "学生姓名不能为空", Toast.LENGTH_SHORT).show();

            }
            else if (stuAge.isEmpty()) {
                Toast.makeText(getApplicationContext(), "学生年龄不能为空", Toast.LENGTH_SHORT).show();
            }
            else if (stuTel.isEmpty()) {
                Toast.makeText(getApplicationContext(), "电话号码不能为空", Toast.LENGTH_SHORT).show();
            }
            else if (stuPassword.isEmpty()) {
                Toast.makeText(getApplicationContext(), "密码不能为空", Toast.LENGTH_SHORT).show();
            }
            else if (stuDate.isEmpty()) {
                Toast.makeText(getApplicationContext(), "入学时间不能为空", Toast.LENGTH_SHORT).show();
            }
            else{
                    Student stu=new Student();
                    stu.setName(stuName);
                    stu.setAge(stuAge);
                    stu.setTel(stuTel);
                    stu.setEnrollment_date(stuDate);
                    stu.setPassword(stuPassword);
                    stu.setFaculty(stuFaculty);
                    stu.setMajor(stuMajor);
                    //Log.d( "num",studentRegister(stu));
                        studentRegister(stu);

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            stu.setStudent_num(str);
                            showDialog(stu);

                            // 你的延时执行的代码
                        }
                    }, 200); // 3000毫秒，即3秒




            }



        }
    }
}