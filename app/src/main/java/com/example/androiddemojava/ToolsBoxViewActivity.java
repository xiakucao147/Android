package com.example.androiddemojava;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import android.Manifest;
import android.database.Cursor;
public class ToolsBoxViewActivity extends AppCompatActivity {
    LinearLayout addressBookLayout ;
    LinearLayout callButton ;;
    LinearLayout sendMessageButton;

    EditText phoneNumberEditText;
    EditText smsContentEditText ;
    protected final int REQUEST_PERMISSION_ADDRESSBOOK = 10;
    protected final int REQUEST_ADDRESSBOOK = 11;
    private final ActivityResultLauncher<Intent> pickContactLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null) {
                            getContacts(data);
                        }
                    }
                }
            });
    private final ActivityResultLauncher<String> requestPermissionLauncher = registerForActivityResult(
            new ActivityResultContracts.RequestPermission(),
            new ActivityResultCallback<Boolean>() {
                @Override
                public void onActivityResult(Boolean isGranted) {
                    if (isGranted) {
                        // 用户成功授予权限
                        Toast.makeText(ToolsBoxViewActivity.this, "授权", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ToolsBoxViewActivity.this, "你拒绝了此应用对读取联系人权限的申请！", Toast.LENGTH_SHORT).show();
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tools_box_view2);
         addressBookLayout = findViewById(R.id.addressBook);
         callButton = findViewById(R.id.callButton);;
         sendMessageButton = findViewById(R.id.sendMessageButton);
         phoneNumberEditText = findViewById(R.id.phoneNumberEditText);
         smsContentEditText = findViewById(R.id.smsContentEditText);

        addressBookLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    // 跳转到联系人界面
                Log.d("ccccc", "点击");
                selectConnection();
                checkContactsPermission();
            }

        });

        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 执行拨打电话的操作
                if(shouldAskPermissions()){
                    askPermissions();
                }
                dialPhoneNumber(phoneNumberEditText.getText().toString()); // 替换为要拨打的实际电话号码
            }
        });
        //点击发送短信
        sendMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = phoneNumberEditText.getText().toString();
                String smsContent = smsContentEditText.getText().toString();

                    sendSms(phoneNumber, smsContent);

            }

        });
        //sendMessageButton
    }
    private void selectConnection() {
        // 使用ActivityResultLauncher启动联系人选择
        pickContactLauncher.launch(new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI));
    }

    private void checkContactsPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // 如果该版本大于等于6.0，检查权限
            if (checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
                // 申请权限
                requestPermissionLauncher.launch(Manifest.permission.READ_CONTACTS);
            } else {
                // 已有权限，可以读取联系人
                //不需要申请
            }
        } else {
            // 低于6.0的版本，无需检查权限，直接读取联系人

        }
    }
    private void sendSms(String phoneNumber, String smsContent) {
        try {
            // 获取 SmsManager
            SmsManager smsManager = SmsManager.getDefault();

            Uri nameUri=Uri.parse("smsto:"+phoneNumberEditText.getText().toString());
            Intent smsIntent=new Intent(Intent.ACTION_SENDTO);
            smsIntent.setData(nameUri);
            smsIntent.putExtra("sms_body",smsContentEditText.getText().toString());

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

    @SuppressLint("Range")
    private void getContacts(Intent data) {
        if (data == null) {
            return;
        }

        Uri contactData = data.getData();
        if (contactData == null) {
            return;
        }
        String name = "";
        String phoneNumber = "";

        Uri contactUri = data.getData();
        Cursor cursor = getContentResolver().query(contactUri, null, null, null, null);
        if (cursor.moveToFirst()) {
            name = cursor
                    .getString(cursor
                            .getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            String hasPhone = cursor
                    .getString(cursor
                            .getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));
            String id = cursor.getString(cursor
                    .getColumnIndex(ContactsContract.Contacts._ID));
            if (hasPhone.equalsIgnoreCase("1")) {
                hasPhone = "true";
            } else {
                hasPhone = "false";
            }
            if (Boolean.parseBoolean(hasPhone)) {
                Cursor phones = getContentResolver()
                        .query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                                null,
                                ContactsContract.CommonDataKinds.Phone.CONTACT_ID
                                        + " = " + id, null, null);
                while (phones.moveToNext()) {
                    phoneNumber = phones
                            .getString(phones
                                    .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                }
                phones.close();
            }
            cursor.close();

            phoneNumberEditText.setText(phoneNumber);
        }
    }
}
