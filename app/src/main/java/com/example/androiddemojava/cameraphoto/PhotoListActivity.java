package com.example.androiddemojava.cameraphoto;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;

import android.widget.ListView;
import android.widget.Toast;

import com.example.androiddemojava.R;

import java.util.ArrayList;

import java.util.List;

public class PhotoListActivity extends AppCompatActivity {
    private List<Photo> photos=new ArrayList<Photo>();
    private ListView photoList;
    private PhotoAdapter photoAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_list);
        photoList=findViewById(R.id.photoList);
        initPhoto();
        photoList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Photo photo=photos.get(i);
                Toast.makeText(PhotoListActivity.this,i+":"+photo.getFileName(),Toast.LENGTH_SHORT).show();
            }
        });
        photoList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Photo p=photos.get(i);
                AlertDialog.Builder myDialog=new AlertDialog.Builder(PhotoListActivity.this);
                myDialog.setTitle("提示");
                myDialog.setIcon(R.mipmap.icon_message);
                myDialog.setMessage("确定删除"+ p.getFileName()+"?");
                myDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        photos.remove(p);//删除集合对象
                        photoAdapter.notifyDataSetChanged();//适配器更新数据
                    }
                });
                myDialog.setNegativeButton("取消",null);
                myDialog.show();

                return true;//返回false会认为长按后放开的那一下为点击
            }
        });
    }

    public void initPhoto() {//从数据库取出以前保存的照片加载到ListView
        photos.clear();
        Photo p1=new Photo();
        p1.setInfo("玲芽之旅");
        p1.setFileName("草太");
        Bitmap b1=BitmapFactory.decodeResource(getResources(),R.mipmap.caotai);//图片变成位图
        p1.setBitmap(b1);
        photos.add(p1);
        Photo p2=new Photo();
        p2.setInfo("玲芽之旅");
        p2.setFileName("玲芽");
        Bitmap b2=BitmapFactory.decodeResource(getResources(),R.mipmap.lingya);
        p2.setBitmap(b2);
        photos.add(p2);
        photoAdapter=new PhotoAdapter(this,photos);
        photoList.setAdapter(photoAdapter);
    }

}