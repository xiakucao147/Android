package com.example.androiddemojava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.qweather.sdk.bean.base.Code;
import com.qweather.sdk.bean.base.Lang;
import com.qweather.sdk.bean.base.Unit;
import com.qweather.sdk.bean.weather.WeatherNowBean;
import com.qweather.sdk.view.HeConfig;
import com.qweather.sdk.view.QWeather;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    Button logging_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logging_btn = findViewById(R.id.logging_btn);
        HeConfig.init("HE2311302145251590", "25db6d634d0b4a17ba4ddd38e86ffa66");
        HeConfig.switchToDevService();
        System.out.println("Weather Now Error:");


        logging_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //get_all_student();
                queryWeather();

            }
        });
    }


    public void queryWeather() {
        QWeather.getWeatherNow(MainActivity.this, "CN101040100", Lang.ZH_HANS, Unit.METRIC, new QWeather.OnResultWeatherNowListener() {
            public static final String TAG = "he_feng_now";

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "onError: ", e);
                System.out.println("Weather Now Error:" + new Gson());
            }

            @Override
            public void onSuccess(WeatherNowBean weatherBean) {
                //Log.i(TAG, "getWeather onSuccess: " + new Gson().toJson(weatherBean));
                System.out.println("获取天气成功： " + new Gson().toJson(weatherBean));
                //先判断返回的status是否正确，当status正确时获取数据，若status不正确，可查看status对应的Code值找到原因

                if (Code.OK == weatherBean.getCode()) {
                    WeatherNowBean.NowBaseBean now = weatherBean.getNow();
                    String tianqi = now.getText();
                    String wendu = now.getTemp() + "℃";
                    String fengli = now.getWindScale();
                    String fengxiang = now.getWindDir();
                    Log.i("WeatherInfo", "当前天气:" + tianqi);
                    Log.i("WeatherInfo", "当前温度:" + wendu);
                    Log.i("WeatherInfo", "风向：" + fengxiang);
                    Log.i("WeatherInfo", "风力：" + fengli + "级");
                    Intent intent = new Intent(MainActivity.this, BottomActivity.class);
                    // 使用 putExtra 方法将数据添加到 Intent 中
                    intent.putExtra("tianqi", tianqi);
                    intent.putExtra("wendu", wendu);
                    intent.putExtra("fengli", fengli);
                    intent.putExtra("fengxiang", fengxiang);

                    // 启动目标 Activity
                    startActivity(intent);
                } else {
                    //在此查看返回数据失败的原因
                    Code code = weatherBean.getCode();
                    System.out.println("失败代码: " + code);
                    //Log.i(TAG, "failed code: " + code);
                }
            }
        });
    }





}


