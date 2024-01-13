package com.example.androiddemojava.sm;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.androiddemojava.R;
import com.example.androiddemojava.cameraphoto.Photo;
import com.example.androiddemojava.cameraphoto.PhotoListActivity;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class StudentSelectActivity extends AppCompatActivity implements View.OnClickListener{

    public String BASEURL= null;
    private String STUDENT_NUM= null;
    private JSONObject jsonObject;
    private JSONArray jsonArray;
    private List<Course> dataSelected=null,dataNoSelected=null;
    private AdapterSelected adapterSelected =null;
    private AdapterNoSelected adapterNoSelected =null;
    private ListView listViewSelected,listViewNoSelected;
    private Context mContext;
    private OkHttpClient client;
    //private ImageButton showCourses;

    private String addURL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_select);
        BASEURL= getString(R.string.backend_baseurl);
        //showCourses=findViewById(R.id.show_courses);
        listViewSelected=findViewById(R.id.listview_selected);
        listViewNoSelected=findViewById(R.id.listview_no_selected);
        //showCourses.setOnClickListener(this);
        mContext=StudentSelectActivity.this;

        client = new OkHttpClient();
        Intent intent = getIntent();
        STUDENT_NUM=intent.getStringExtra("student_num");
        dataSelected= new LinkedList<Course>();
        dataNoSelected = new LinkedList<Course>();
        get_selected_courses(STUDENT_NUM);
        get_no_selected_courses(STUDENT_NUM);
        listViewSelected.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Course p = dataSelected.get(i);
                Toast.makeText(StudentSelectActivity.this,"点击",Toast.LENGTH_SHORT).show();
                AlertDialog.Builder myDialog=new AlertDialog.Builder(StudentSelectActivity.this);
                myDialog.setTitle("退选课程");
                myDialog.setIcon(R.mipmap.icon_message);
                myDialog.setMessage("退选："+ p.getName()+"?");
                myDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dataSelected.remove(p);//删除集合对象
                        dataNoSelected.add(p);
                        Grade g=new Grade(STUDENT_NUM,p.getNo(),"-1");
                        del_grade(g);
                        adapterNoSelected.notifyDataSetChanged();//适配器更新数据
                        adapterSelected.notifyDataSetChanged();
                    }
                });
                myDialog.setNegativeButton("取消",null);
                myDialog.show();
                //get_selected_courses(STUDENT_NUM);
                //get_no_selected_courses(STUDENT_NUM);
            }
        });

        listViewNoSelected.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Course p = dataNoSelected.get(i);
                Toast.makeText(StudentSelectActivity.this,"点击",Toast.LENGTH_SHORT).show();
                AlertDialog.Builder myDialog=new AlertDialog.Builder(StudentSelectActivity.this);
                myDialog.setTitle("添加课程");
                myDialog.setIcon(R.mipmap.icon_message);
                myDialog.setMessage("添加："+ p.getName()+"?");
                myDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dataNoSelected.remove(p);//删除集合对象
                        dataSelected.add(p);
                        Grade g=new Grade(STUDENT_NUM,p.getNo(),"-1");
                        add_grade(g);
                        adapterNoSelected.notifyDataSetChanged();//适配器更新数据
                        adapterSelected.notifyDataSetChanged();
                    }
                });
                myDialog.setNegativeButton("取消",null);
                myDialog.show();
                //get_selected_courses(STUDENT_NUM);
                //get_no_selected_courses(STUDENT_NUM);
            }
        });

    }
    public void add_grade(Grade grade){
        client = new OkHttpClient();
        addURL="/grade/add/";
        String param = new Gson().toJson(grade);

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
                    //if(jsonObject.getString("error_message").equals("ok")) flag[0] =true ;
                    //student.setStudent_num(jsonObject.getString("error_message"));
                    // str=student.getStudent_num();
                    //Log.d( "erro",student.getStudent_num());



                } catch (JSONException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }


            }
        }.start();
    }
    public void del_grade(Grade grade){
        client = new OkHttpClient();
        addURL="/grade/delete/";
        String param = new Gson().toJson(grade);

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
                    //if(jsonObject.getString("error_message").equals("ok")) flag[0] =true ;
                    //student.setStudent_num(jsonObject.getString("error_message"));
                    // str=student.getStudent_num();
                    //Log.d( "erro",student.getStudent_num());



                } catch (JSONException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }


            }
        }.start();
    }




    public void get_selected_courses(String stu_no){
        addURL="/grade/selected/?stu_num=";
        final Request request=new Request.Builder()
                .url(BASEURL+addURL+stu_no)
                .build();
        final Call call=client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("onFailure getMessage",e.getMessage());
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d( " response.message() ",response.message());
                final String responseString = response.body().string();
                System.out.println(responseString);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            jsonArray=new JSONArray(responseString);
                            //jsonArray=jsonObject1.optJSONArray("data");
                            dataSelected.clear();
                            for(int i=0;i<jsonArray.length();i++){
                                jsonObject=jsonArray.optJSONObject(i);
                                String no=jsonObject.getString("no");
                                String name=jsonObject.getString("name");
                                String score=jsonObject.getString("score");
                                Course course=new Course(no,name,score);
                                dataSelected.add(course);
                            }
                            adapterSelected =new AdapterSelected((LinkedList<Course>) dataSelected,mContext);
                            listViewSelected.setAdapter(adapterSelected);
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
            }
        });
    }


    public void get_all_student(){
        OkHttpClient okHttpClient = new OkHttpClient();
        final Request request=new Request.Builder()
                .url(BASEURL)
                .build();
        final Call call=okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("onFailure getMessage",e.getMessage());
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d( " response.message() ",response.message());
                String responseBody = response.body().string();
                System.out.println(responseBody);
            }
        });
    }
    public void get_no_selected_courses(String stu_no){
        addURL="/grade/no_selected/?stu_num=";
        final Request request=new Request.Builder()
                .url(BASEURL+addURL+stu_no)
                .build();
        final Call call=client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("onFailure getMessage",e.getMessage());
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d( " response.message() ",response.message());
                final String responseString = response.body().string();
                System.out.println(responseString);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            jsonArray=new JSONArray(responseString);
                            //jsonArray=jsonObject1.optJSONArray("data");
                            dataNoSelected.clear();
                            for(int i=0;i<jsonArray.length();i++){
                                jsonObject=jsonArray.optJSONObject(i);
                                String no=jsonObject.getString("no");
                                String name=jsonObject.getString("name");
                                String score=jsonObject.getString("score");
                                Course course=new Course(no,name,score);
                                dataNoSelected.add(course);
                            }
                            adapterNoSelected =new AdapterNoSelected((LinkedList<Course>) dataNoSelected,mContext);
                            listViewNoSelected.setAdapter(adapterNoSelected);
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
            }
        });



    }

    @Override
    public void onClick(View view) {
        int viewId=view.getId();
//        if(viewId==R.id.show_courses){
//            get_selected_courses("02170902");
//            get_no_selected_courses("02170902");
//        }
    }
}