package com.example.androiddemojava;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ToolsBoxViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tools_box_view2);
        LinearLayout addressBookLayout = findViewById(R.id.addressBook);
        LinearLayout callButton = findViewById(R.id.callButton);;
        LinearLayout sendMessageButton = findViewById(R.id.sendMessageButton);
        EditText phoneNumberEditText = findViewById(R.id.phoneNumberEditText);
        EditText smsContentEditText = findViewById(R.id.smsContentEditText);
        // 找到 LinearLayout

        // 设置点击事件
        addressBookLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 创建单选列表项
                final String[] items = {"勒布朗詹姆斯", "凯里欧文", "史蒂芬库里", "吉米巴特勒"};
                final String[] Tels  = {"0024152287342","0034159987342","0044135787342","0054155789642"};

                // 创建 AlertDialog.Builder 对象
                AlertDialog.Builder builder = new AlertDialog.Builder(ToolsBoxViewActivity.this);
                builder.setTitle("Select a contact");

                // 设置单选列表项
                builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 用户点击单选列表项时的处理
                        String selectedContact = items[which];
                        String selectedTel = Tels[which];

                        Toast.makeText(ToolsBoxViewActivity.this, "Selected contact: " + selectedContact, Toast.LENGTH_SHORT).show();
                        phoneNumberEditText.setText(selectedTel);
                        // 关闭对话框
                        dialog.dismiss();
                    }
                });

                // 创建并显示对话框
                AlertDialog dialog = builder.create();
                dialog.show();

            }

        });

        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 执行拨打电话的操作
                if(shouldAskPermissions()){
                    askPermissions();
                }
                dialPhoneNumber("10086"); // 替换为要拨打的实际电话号码
            }
        });
        //点击发送短信
        sendMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = phoneNumberEditText.getText().toString();
                String smsContent = smsContentEditText.getText().toString();
                // 确保电话号码和短信内容不为空
//                if (!phoneNumber.isEmpty() && !smsContent.isEmpty()) {
//                    // 执行发送短信的操作
                    sendSms(phoneNumber, smsContent);
//                } else {
//                    // 处理电话号码或短信内容为空的情况，这里简单显示一个 Toast
//                    Toast.makeText(ToolsBoxViewActivity.this, "Please enter a phone number and SMS content", Toast.LENGTH_SHORT).show();
//                }
            }

        });
        //sendMessageButton
    }
    private void sendSms(String phoneNumber, String smsContent) {
        try {
            // 获取 SmsManager
            SmsManager smsManager = SmsManager.getDefault();

            Uri nameUri=Uri.parse("smsto:123");
            Intent smsIntent=new Intent(Intent.ACTION_SENDTO);
            smsIntent.setData(nameUri);
            smsIntent.putExtra("sms_body","wellcom");
            // 发送短信
            //smsManager.sendTextMessage(phoneNumber, null, smsContent, null, null);//这个不能用麻了

            startActivity(smsIntent);

            // 短信发送成功，显示提示
            Toast.makeText(ToolsBoxViewActivity.this, "SMS sent successfully", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            // 发送短信失败，显示错误信息
            Toast.makeText(ToolsBoxViewActivity.this, "Failed to send SMS", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
    private void dialPhoneNumber(String phoneNumber) {
        // 创建一个拨打电话的 Intent
        Intent dialIntent = new Intent(Intent.ACTION_DIAL);
        dialIntent.setData(Uri.parse("tel:"+phoneNumber));
        startActivity(dialIntent);
        // 检查系统是否有应用可以处理这个 Intent
//        if (dialIntent.resolveActivity(getPackageManager()) != null) {
//            // 启动拨打电话的 Activity
//            startActivity(dialIntent);
//        } else {
//            // 如果没有应用可以处理这个 Intent，可以进行适当的处理，比如显示错误信息
//            // 或者提示用户安装电话应用
//            // 这里简单显示一个 Toast
//            Toast.makeText(this, "No app to handle this action", Toast.LENGTH_SHORT).show();
//        }
    }
    protected boolean shouldAskPermissions(){
        return (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1);//判断版本号
    }
    protected void askPermissions(){
        String[] permissions = {
                "android.permission.CALL_PHONE"
        };
        int requestCode = 200;
        requestPermissions(permissions,requestCode);
    }
}
