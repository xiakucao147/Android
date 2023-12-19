package com.example.androiddemojava;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class TeachCaseFragment extends Fragment implements View.OnClickListener{



    LinearLayout boxInterfaceDesign;
    LinearLayout boxCheckbox;

    private ActivityResultLauncher<Intent> activityResultLauncher;
//    LinearLayout callButton = findViewById(R.id.callButton);;
//    LinearLayout sendMessageButton = findViewById(R.id.sendMessageButton);
//    EditText phoneNumberEditText = findViewById(R.id.phoneNumberEditText);
//    EditText smsContentEditText = findViewById(R.id.smsContentEditText);

    public TeachCaseFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_teach_case, container, false);
        // Initialize LinearLayouts
        boxInterfaceDesign = fragmentView.findViewById(R.id.boxInterfaceDesign);
        boxCheckbox=fragmentView.findViewById(R.id.boxCheckbox);
        // Set click listeners for LinearLayouts
        boxInterfaceDesign.setOnClickListener(this);
        boxCheckbox.setOnClickListener(this);


        Intent intent=new Intent(getActivity(),CheckBoxActivity.class);
        activityResultLauncher=registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if(result.getResultCode()== Activity.RESULT_OK){
                        Intent data=result.getData();
                        if(data!=null){

                        }
                    }
                }
        );

        return fragmentView;
    }

    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        if (viewId == R.id.boxInterfaceDesign) {
            Intent intent=new Intent();
            intent.setClass(getActivity(), InterfaceDesignActivity.class);
            startActivity(intent);
            // Handle click on boxTools
        }
        else if(viewId==R.id.boxCheckbox){
            Log.d("TeachCaseFragment", "boxCheckbox clicked");

        Intent intent=new Intent(getActivity(),CheckBoxActivity.class);



            //            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//            builder.setTitle("选择球员和教练");
            // 设置多选框
//            builder.setMultiChoiceItems(players, null, new DialogInterface.OnMultiChoiceClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which, boolean isChecked) {
//                    // 处理多选框点击事件
//                    Toast.makeText(getActivity(), "选择了：" + players[which], Toast.LENGTH_SHORT).show();
//                }
//            });

            // 设置单选框
//            builder.setSingleChoiceItems(coachs, -1, new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    // 处理单选框点击事件
//                    Toast.makeText(getActivity(), "选择了：" + coachs[which], Toast.LENGTH_SHORT).show();
//                }
//            });
//            AlertDialog dialog = builder.create();
//            dialog.show();




        }
    }
}