package com.example.androiddemojava;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.ViewSwitcher;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class PhotoScanActivity extends AppCompatActivity
        implements ViewSwitcher.ViewFactory ,View.OnClickListener{

    private ImageButton btn_pre,btn_back;
    private ImageSwitcher imageSwitcher;
    private int[] imagesId={R.mipmap.bg2,R.mipmap.bg2,R.mipmap.bg2};
    private Bitmap[] bitmaps;
    private int imageIndex=0;
    private Spinner basicSpinner;
    private String url1="https://ts1.cn.mm.bing.net/th/id/R-C.937ea0ecd25c35043caa87c88bc95942?rik=ZKc4DPYSDIZg6A&riu=http%3a%2f%2fimages.qiecdn.com%2fnews%2f20220718%2faHR0cDovL2luZXdzLmd0aW1nLmNvbS9uZXdzYXBwX21hdGNoLzAvMTUwOTY2MTU4NzUvMA&ehk=Cz%2fWgdNpV4C4mCBrvjQCASTOrvU31ccfFsljui36noI%3d&risl=&pid=ImgRaw&r=0",
    url2="https://c-ssl.duitang.com/uploads/blog/202103/14/20210314125747_c9d9c.jpg",
    url3="https://c-ssl.duitang.com/uploads/blog/202010/11/20201011144506_d9fb5.jpg";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_scan);
        btn_back=findViewById(R.id.next);
        btn_pre=findViewById(R.id.previous);
        imageSwitcher=findViewById(R.id.imagebrowner);

        imageSwitcher.setFactory(this);
        imageSwitcher.setImageResource(imagesId[imageIndex]);
        btn_pre.setOnClickListener(this);
        btn_back.setOnClickListener(this);
        bitmaps = new Bitmap[3];
        setDownImage(url1,0);
        setDownImage(url2,1);
        setDownImage(url3,2);
    }

    @Override
    public View makeView() {
        ImageView imageView=new ImageView(this);
        return imageView;
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.next){

            imageIndex++;
            if(imageIndex>bitmaps.length-1){
                imageIndex=0;
            }
            imageSwitcher.setInAnimation(this,R.anim.left_in);
            imageSwitcher.setOutAnimation(this,R.anim.rigth_out);

        }
        else if(view.getId()==R.id.previous){

       imageIndex--;
            if(imageIndex<0){
                imageIndex= bitmaps.length-1;
            }
            imageSwitcher.setInAnimation(this,R.anim.right_in);
            imageSwitcher.setOutAnimation(this,R.anim.left_out);

        }
        //imageSwitcher.setImageResource(imagesId[imageIndex]);
        imageSwitcher.setImageDrawable(new BitmapDrawable(getResources(),bitmaps[imageIndex]));
    }
    private void setDownImage(final String fileUrl,int index)
    {
        new Thread(){
            public void run(){
                try{
                    URL url=new URL(fileUrl);
                    HttpURLConnection httpURLConnection=(HttpURLConnection)  url.openConnection();
                    httpURLConnection.setConnectTimeout(5*1000);
                    httpURLConnection.connect();
                    if(httpURLConnection.getResponseCode()==200){
                        InputStream is=httpURLConnection.getInputStream();
                        final Bitmap bitmap= BitmapFactory.decodeStream(is);
                        PhotoScanActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                bitmaps[index]=bitmap;
                            }
                        });
                    }
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }.start();
    }
}