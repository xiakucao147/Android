package com.example.androiddemojava.cameraphoto;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.util.Base64;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class PhotoDAO {
    SQLiteDatabase sqLiteDatabase=null;
    Context context;
    PhotoDAO(Context context){
        this.context=context;
    }

    //构造方法
    public void  connect(){
        DataBaseAccess dba=new DataBaseAccess(context,"photoDb",1);
        sqLiteDatabase=dba.getReadableDatabase();
        //getReadableDatabase和getWritableDatabase可以创建或打开一个现有的数据库(如果数据库已存在则直接打开，否则创建一个新的数据库)
    }

    //图片插入
    public long insert(Photo photo){
        connect();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        photo.getBitmap().compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] byteArray = baos.toByteArray();
        String encodedImage = Base64.encodeToString(byteArray, Base64.DEFAULT);

        ContentValues values=new ContentValues();
        values.put("name",photo.getFileName());
        values.put("info",photo.getInfo());
        values.put("fileName",photo.getFileName());
        //values.put("bitmap",encodedImage);
        values.put("filePath",photo.getFilePath());
        long n=sqLiteDatabase.insert("photo",null,values);
        Toast.makeText(context, "插入成功"+n, Toast.LENGTH_SHORT).show();
        sqLiteDatabase.close();
        return n;
    }
//图片删除

    public void delete(int id){

        connect();
        String sql="delete from photo where id="+id; //注意没有delete * from
        sqLiteDatabase.execSQL(sql);
        Toast.makeText(context, "删除成功", Toast.LENGTH_SHORT).show();
        sqLiteDatabase.close();
    }

//图片查询

    public List<Photo> findPhoto(){

        connect();
        List<Photo> photoList=new ArrayList<>();
        String sql="select * from photo";
        Cursor cursor=sqLiteDatabase.rawQuery(sql,null);
        while(cursor.moveToNext()){
            @SuppressLint("Range") int id=cursor.getInt(cursor.getColumnIndex("id"));
            @SuppressLint("Range") String name=cursor.getString(cursor.getColumnIndex("name"));
            @SuppressLint("Range") String info=cursor.getString(cursor.getColumnIndex("info"));
            @SuppressLint("Range") String fileName=cursor.getString(cursor.getColumnIndex("fileName"));
            @SuppressLint("Range") String filePath=cursor.getString(cursor.getColumnIndex("filePath"));
            Photo p=new Photo();
            p.setId(id);
            p.setFileName(name);
            p.setInfo(info);
            p.setFilePath(filePath);
            p.setFileName(fileName);
            photoList.add(p);
        }
        sqLiteDatabase.close();
        return photoList;
    }
}