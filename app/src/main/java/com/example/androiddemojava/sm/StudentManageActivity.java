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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androiddemojava.R;
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

public class StudentManageActivity extends AppCompatActivity implements View.OnClickListener{
    public String BASEURL= null;
    private String STUDENT_NUM= null;
    private ImageButton btn_search;
    private EditText edittext_search;
    private Context mContext=null;
    private OkHttpClient client;
    AdapterStudent adapterStudent=null;
    private ListView listViewStudent;
    private JSONObject jsonObject;
    private JSONArray jsonArray;
    private List<Student> dataStudentList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_manage);
        BASEURL= getString(R.string.backend_baseurl);
        mContext=StudentManageActivity.this;
        dataStudentList = new LinkedList<Student>();
        client = new OkHttpClient();

        btn_search=findViewById(R.id.btn_search);
        edittext_search=findViewById(R.id.edittext_search);
        listViewStudent=findViewById(R.id.list_view_student);

        btn_search.setOnClickListener(this);

        listViewStudent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Student s = dataStudentList.get(i);
                STUDENT_NUM = s.getStudent_num();
                Toast.makeText(StudentManageActivity.this,"点击",Toast.LENGTH_SHORT).show();
                AlertDialog.Builder myDialog=new AlertDialog.Builder(StudentManageActivity.this);
                myDialog.setTitle("学生详细信息以及选课情况");
                myDialog.setIcon(R.mipmap.icon_message);
                myDialog.setMessage("查看选课情况");
                //showDialogStudentSelecteInfo(s);
                 View studentInfo = View.inflate(StudentManageActivity.this,R.layout.dialog_student_info,null);

                TextView tv_name      = studentInfo.findViewById(R.id.tv_name   );
                TextView tv_sex       = studentInfo.findViewById(R.id.tv_sex    );
                TextView tv_faculty   = studentInfo.findViewById(R.id.tv_faculty);
                TextView tv_major     = studentInfo.findViewById(R.id.tv_major  );
                TextView tv_no        = studentInfo.findViewById(R.id.tv_no     );

                tv_name   .setText(s.getName());
                tv_sex    .setText(s.getSex());
                tv_faculty.setText(s.getFaculty());
                tv_major  .setText(s.getMajor());
                tv_no     .setText(s.getStudent_num());
                myDialog.setView(studentInfo);
                myDialog.setPositiveButton("查看选课情况", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent=new Intent();
                        intent.setClass(StudentManageActivity.this, StudentSelectActivity.class);
                        intent.putExtra("student_num",s.getStudent_num());
                        startActivity(intent);


                    }
                });
                myDialog.setNegativeButton("取消",null);
                myDialog.show();
                //get_selected_courses(STUDENT_NUM);
                //get_no_selected_courses(STUDENT_NUM);
            }
        });

    }
    public void showDialogStudentSelecteInfo(Student stu){

    }
    @Override
    public void onClick(View view) {
        int viewId=view.getId();
        if(viewId==R.id.btn_search){
            getStudentSearch(edittext_search.getText().toString());
        }
    }
    public void getStudentSearch(String stu_no) {
        String addURL = "/student/search/?name=";
        final Request request = new Request.Builder()
                .url(BASEURL + addURL + stu_no)
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("onFailure getMessage", e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d(" response.message() ", response.message());
                final String responseString = response.body().string();
                System.out.println(responseString);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            jsonArray = new JSONArray(responseString);
                            //jsonArray=jsonObject1.optJSONArray("data");

                            dataStudentList.clear();
                            for (int i = 0; i < jsonArray.length(); i++) {
                                jsonObject = jsonArray.optJSONObject(i);
                                String name       =  jsonObject.getString("name")   ;
                                String student_num        =  jsonObject.getString("student_num")    ;
                                String major      =  jsonObject.getString("major")  ;
                                String sex        =  jsonObject.getString("sex")    ;
                                String faculty    =  jsonObject.getString("faculty");

                                Student student = new Student();
                                student.setName(name);
                                student.setStudent_num(student_num);
                                student.setMajor(major);
                                student.setSex(major);
                                student.setSex(sex);
                                student.setFaculty(faculty);

                                dataStudentList.add(student);
                            }
                            adapterStudent = new AdapterStudent((LinkedList<Student>) dataStudentList, mContext);
                            listViewStudent.setAdapter(adapterStudent);

                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
            }
        });
    }
}