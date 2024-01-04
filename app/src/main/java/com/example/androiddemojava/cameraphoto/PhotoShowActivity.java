package com.example.androiddemojava.cameraphoto;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.example.androiddemojava.R;

import java.util.ArrayList;
import java.util.List;

public class PhotoShowActivity extends AppCompatActivity implements ViewSwitcher.ViewFactory {

    private ImageSwitcher imageSwitcher;
    private TextView pic_name;
    private TextView pic_info;
    private int postion;
    private List<Photo> photos=new ArrayList<>();
    private float startX,startY,offsetX,offsetY;
    private PhotoDAO photoDao=new PhotoDAO(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_show);
    }

    @Override
    public View makeView() {
        return null;
    }
}