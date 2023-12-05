package com.example.androiddemojava;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

public class TeachCaseFragment extends Fragment {



//    LinearLayout addressBookLayout = findViewById(R.id.addressBook);
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_teach_case, container, false);
    }
}