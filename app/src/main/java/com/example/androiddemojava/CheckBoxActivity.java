package com.example.androiddemojava;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class CheckBoxActivity extends AppCompatActivity {

    private RadioButton radioButton1 ,radioButton2,radioButton3,radioButton4;
    private RadioGroup radioGroup;
    private CheckBox checkBox1,checkBox2;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_box);

        radioGroup=findViewById(R.id.radioGroup);
        radioButton1=findViewById(R.id.radiobutton1);
        radioButton2=findViewById(R.id.radiobutton2);
        radioButton3=findViewById(R.id.radiobutton3);
        radioButton4=findViewById(R.id.radiobutton4);

        checkBox1=findViewById(R.id.checkbox1);
        checkBox2=findViewById(R.id.checkbox2);

        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                StringBuilder message = new StringBuilder("你选择了：");

                int selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();
                if (selectedRadioButtonId != -1) {
                    RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);
                    message.append(selectedRadioButton.getText()).append("\n");
                }

                if (checkBox1.isChecked()) {
                    message.append(checkBox1.getText()).append("\n");
                }
                if (checkBox2.isChecked()) {
                    message.append(checkBox2.getText()).append("\n");
                }

                showMessageDialog(message.toString());
            }
            }
        );

    }
    private void showMessageDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // 可以在这里添加点击确定按钮后的操作
            }
        });
        builder.show();
    }
}