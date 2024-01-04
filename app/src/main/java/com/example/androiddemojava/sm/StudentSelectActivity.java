package com.example.androiddemojava.sm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.androiddemojava.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class StudentSelectActivity extends AppCompatActivity implements View.OnClickListener{

    public final String baseURL="http://192.168.117.62:3000";
    private JSONObject jsonObject1,jsonObject2;
    private JSONArray jsonArray;
    private List<Course> mData=null;
    private AdapterSelected adapterSelected =null;
    private AdapterNoSelected adapterNoSelected =null;
    private ListView listViewSelected,listViewNoSelected;
    private Context mContext;
    private OkHttpClient client;
    private ImageButton showCourses;

    private String addURL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_select);
        showCourses=findViewById(R.id.show_courses);
        listViewSelected=findViewById(R.id.listview_selected);
        listViewNoSelected=findViewById(R.id.listview_no_selected);
        showCourses.setOnClickListener(this);
        mContext=StudentSelectActivity.this;

        client = new OkHttpClient();
    }

    public void get_selected_courses(String stu_no){
        addURL="/grade/selected/?stu_num=";
        final Request request=new Request.Builder()
                .url(baseURL+addURL+stu_no)
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
                            mData= new LinkedList<Course>();
                            for(int i=0;i<jsonArray.length();i++){
                                jsonObject2=jsonArray.optJSONObject(i);
                                String name=jsonObject2.getString("name");
                                String score=jsonObject2.getString("score");
                                Course course=new Course(name,score);
                                mData.add(course);
                            }
                            adapterSelected =new AdapterSelected((LinkedList<Course>) mData,mContext);
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
                .url(baseURL)
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
                .url(baseURL+addURL+stu_no)
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
                            mData= new LinkedList<Course>();
                            for(int i=0;i<jsonArray.length();i++){
                                jsonObject2=jsonArray.optJSONObject(i);
                                String name=jsonObject2.getString("name");
                                String score=jsonObject2.getString("score");
                                Course course=new Course(name,score);
                                mData.add(course);
                            }
                            adapterNoSelected =new AdapterNoSelected((LinkedList<Course>) mData,mContext);
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
        if(viewId==R.id.show_courses){
            get_selected_courses("02170902");
            get_no_selected_courses("02170902");
        }
    }
}