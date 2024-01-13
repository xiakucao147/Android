package com.example.androiddemojava.cameraphoto;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DataBaseAccess extends SQLiteOpenHelper {

    public DataBaseAccess(Context context,String name,int version){
        super(context,name,null,version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        System.out.println("创建photo表格");
        String sql="create table photo(\n "+
                "id integer primary key autoincrement ,\n" +
                " name text, \n"+
                " info text,\n"+
                " fileName text,\n"+
                " filePath text\n"+
                ")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
