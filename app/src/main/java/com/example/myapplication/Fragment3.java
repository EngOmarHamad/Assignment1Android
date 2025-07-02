package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.myapplication.databinding.Fragment3Binding;

public class Fragment3 extends Fragment {

    private static final String ARG_NAME = "user_name";
    private String userName;
    private Fragment3Binding binding;
    private CheckBox checkBoxConfirm;

    public Fragment3() {
        // Required empty public constructor
    }

    public static Fragment3 newInstance(String name) {
        Fragment3 fragment = new Fragment3();
        Bundle args = new Bundle();
        args.putString(ARG_NAME, name);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            userName = getArguments().getString(ARG_NAME);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = Fragment3Binding.inflate(inflater, container, false);
        checkBoxConfirm = binding.checkBoxConfirm;
        binding.tvUserName.setText(String.format("Welcome, %s", userName));
        return binding.getRoot();
    }
    public boolean isCheckboxChecked() {
        return checkBoxConfirm != null && checkBoxConfirm.isChecked();
    }
    public void setOnCheckboxChangeListener(CompoundButton.OnCheckedChangeListener listener) {
        if (checkBoxConfirm != null) {
            checkBoxConfirm.setOnCheckedChangeListener(listener);
        }
    }
}
