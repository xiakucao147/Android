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
    LinearLayout boxPhotoSwitcher;
    LinearLayout downBox;
    private ActivityResultLauncher<Intent> activityResultLauncher;

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
        downBox=fragmentView.findViewById(R.id.boxPhotoDownload);
        boxPhotoSwitcher=fragmentView.findViewById(R.id.boxPhotoScan);
        // Set click listeners for LinearLayouts
        boxInterfaceDesign.setOnClickListener(this);
        boxCheckbox.setOnClickListener(this);
        downBox.setOnClickListener(this);
        boxPhotoSwitcher.setOnClickListener(this);

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
            Intent intent=new Intent();
            intent.setClass(getActivity(),CheckBoxActivity.class);
            startActivity(intent);


        }
        else if(viewId==R.id.boxPhotoDownload){
            Intent intent=new Intent();
            intent.setClass(getActivity(),PhotoDownLoadActivity.class);
            startActivity(intent);


        }
        else if(viewId==R.id.boxPhotoScan){
            Intent intent=new Intent();
            intent.setClass(getActivity(),PhotoScanActivity.class);
            startActivity(intent);
        }
    }
}