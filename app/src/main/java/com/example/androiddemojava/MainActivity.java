
package com.example.androiddemojava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

    public class MainActivity extends AppCompatActivity implements View.OnClickListener {

        private Button btn1;
        private Button btn2;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            // 初始时加载 LeftFragment
            LeftFragment fragment1 = new LeftFragment();
            //getSupportFragmentManager().beginTransaction().replace(R.id.main_content, fragment1).commit();

            btn1 = findViewById(R.id.btn1);
            btn2 = findViewById(R.id.btn2);

            btn1.setOnClickListener(this);
            btn2.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int viewId = view.getId();

            if (viewId == R.id.btn1) {
                // 切换到 LeftFragment
                LeftFragment fragment1 = new LeftFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.main_content, fragment1).commit();
            } else if (viewId == R.id.btn2) {
                // 切换到 RightFragment
                RightFragment fragment2 = new RightFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.main_content, fragment2).commit();
            }
        }

}

