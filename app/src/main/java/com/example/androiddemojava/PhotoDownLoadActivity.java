package com.example.androiddemojava;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class PhotoDownLoadActivity extends AppCompatActivity {

    private Button downBtn;
    private ImageView myImage;
    private SeekBar seekbarFlip,seekbarSize;
    private RadioGroup radioGroup;
    private String url="https://img-prod-cms-rt-microsoft-com.akamaized.net/cms/api/am/imageFileData/RE4wtd9?ver=8b09";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_down_load);
        downBtn=findViewById(R.id.download_btn);
        myImage=findViewById(R.id.myImage);
        seekbarFlip=findViewById(R.id.seekbar_Flip);
        seekbarSize=findViewById(R.id.seekbar_Size);
        radioGroup = findViewById(R.id.radioGroupImage);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==R.id.radiobutton_Image1){
                    url="https://tse1-mm.cn.bing.net/th/id/OIP-C.l85Mik2WeAqphVyK9ip6UwHaE7?rs=1&pid=ImgDetMain";
                }
                else if(checkedId==R.id.radiobutton_Image2){
                    url="https://ts1.cn.mm.bing.net/th/id/R-C.937ea0ecd25c35043caa87c88bc95942?rik=ZKc4DPYSDIZg6A&riu=http%3a%2f%2fimages.qiecdn.com%2fnews%2f20220718%2faHR0cDovL2luZXdzLmd0aW1nLmNvbS9uZXdzYXBwX21hdGNoLzAvMTUwOTY2MTU4NzUvMA&ehk=Cz%2fWgdNpV4C4mCBrvjQCASTOrvU31ccfFsljui36noI%3d&risl=&pid=ImgRaw&r=0";
                }
                else if(checkedId==R.id.radiobutton_Image3){
                    url="https://tse4-mm.cn.bing.net/th/id/OIP-C.XUFCAMV_WV4MWiG0amUm9QHaK2?rs=1&pid=ImgDetMain";
                }
                else if(checkedId==R.id.radiobutton_Image4){
                    url="https://ts1.cn.mm.bing.net/th/id/R-C.61f389078501baceb43a981ce32d4b3f?rik=wPa6KPIa%2fnB2Ug&riu=http%3a%2f%2fpcs4.clubstatic.lenovo.com.cn%2fdata%2fattachment%2fforum%2f201710%2f06%2f165314zidizfdh2fufbkf8.jpg&ehk=2qLPvDk7JhN5Z%2b1sfiUehQYUZjAqzYsSj%2fOJYASftQ8%3d&risl=&pid=ImgRaw&r=0";
                }
            }
        });
        downBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDownImage(url);
            }
        });
        seekbarFlip.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                myImage.setRotation(progress * 3); // 根据进度改变旋转角度
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        seekbarSize.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                myImage.setScaleX(1 + progress * 0.02f); // 根据进度改变缩放比例
                myImage.setScaleY(1 + progress * 0.02f);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });



    }
    private void setDownImage(final String fileUrl)
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
                        PhotoDownLoadActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                myImage.setImageBitmap(bitmap);
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