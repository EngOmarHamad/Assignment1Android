package com.example.myapplication;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.myapplication.databinding.Fragment2Binding;

import java.util.Objects;
public class Fragment2 extends Fragment {
    public static  Fragment2Binding fragment2Binding;
    private OnContinueListener listener;
    public interface OnContinueListener {
        void onContinueClicked(String name);
    }
    public Fragment2() {
    }
    public static Fragment2 newInstance() {
        return new Fragment2();
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnContinueListener) {
            listener = (OnContinueListener) context;
        } else {
            throw new ClassCastException(context.toString() + " must implement OnContinueListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    public boolean isValidInput() {
        String name = Objects.requireNonNull(fragment2Binding.etName.getText()).toString().trim();
        String email = Objects.requireNonNull(fragment2Binding.etEmail.getText()).toString().trim();
        String ageStr = Objects.requireNonNull(fragment2Binding.etAge.getText()).toString().trim();
        if (name.isEmpty()) {
            fragment2Binding.etName.setError("Name is required");
            Toast.makeText(fragment2Binding.getRoot().getContext(), "Name is required", Toast.LENGTH_SHORT).show();

            return false;
        }

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            fragment2Binding.etEmail.setError("Enter a valid email");
            Toast.makeText(fragment2Binding.getRoot().getContext(), "Enter a valid email", Toast.LENGTH_SHORT).show();

            return false;
        }

        if (ageStr.isEmpty()) {
            fragment2Binding.etAge.setError("Age is required");
            Toast.makeText(fragment2Binding.getRoot().getContext(), "Age is required", Toast.LENGTH_SHORT).show();

            return false;
        }

        int age;
        try {
            age = Integer.parseInt(ageStr);
            if (age <= 0) {
                fragment2Binding.etAge.setError("Enter a valid positive age");
                Toast.makeText(fragment2Binding.getRoot().getContext(), "Enter a valid positive age", Toast.LENGTH_SHORT).show();

                return false;
            }
        } catch (NumberFormatException e) {
            fragment2Binding.etAge.setError("Enter a valid number");
            Toast.makeText(fragment2Binding.getRoot().getContext(), "Enter a valid number", Toast.LENGTH_SHORT).show();

            return false;
        }

        int selectedGenderId = fragment2Binding.radioGroupGender.getCheckedRadioButtonId();
        if (selectedGenderId == -1) {
            Toast.makeText(fragment2Binding.getRoot().getContext(), "Please select a gender", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragment2Binding = Fragment2Binding.inflate(inflater, container, false);
        return fragment2Binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        fragment2Binding = null;
    }
}
