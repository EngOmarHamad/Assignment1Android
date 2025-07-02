package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;
import com.example.myapplication.databinding.ActivityMainBinding;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements Fragment2.OnContinueListener {

    private ActivityMainBinding binding;
    private int currentFragment = 1;
    Fragment1 fragment1;
    Fragment2 fragment2;
    Fragment3 fragment3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        fragment1 = Fragment1.newInstance();
        fragment2 = Fragment2.newInstance();

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, fragment1)
                .commit();

        binding.buttonContinue.setEnabled(true);

        getSupportFragmentManager().addOnBackStackChangedListener(() -> {
            Fragment current = getSupportFragmentManager().findFragmentById(R.id.fragment_container);

            if (current instanceof Fragment1) {
                currentFragment = 1;
                SetBtnContinueEnabled();
            } else if (current instanceof Fragment2) {
                currentFragment = 2;
                SetBtnContinueEnabled();
            } else if (current instanceof Fragment3) {
                currentFragment = 3;
                binding.buttonContinue.setEnabled(fragment3 != null && fragment3.isCheckboxChecked());
                binding.buttonContinue.setText(fragment3 != null && fragment3.isCheckboxChecked() ? "Finish" : "Continue");
            }
        });

        binding.buttonContinue.setOnClickListener(view -> {
            if (currentFragment == 1) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, fragment2)
                        .addToBackStack(null)
                        .commit();
                currentFragment = 2;

            } else if (currentFragment == 2) {
                if (fragment2.isValidInput()) {
                    String name = Objects.requireNonNull(Fragment2.fragment2Binding.etName.getText()).toString();

                    if (!TextUtils.isEmpty(name)) {
                        fragment3 = Fragment3.newInstance(name);

                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.fragment_container, fragment3)
                                .addToBackStack(null)
                                .commit();

                        currentFragment = 3;
                        binding.buttonContinue.setEnabled(false);

                        binding.buttonContinue.post(() -> {
                            fragment3.setOnCheckboxChangeListener((buttonView, isChecked) -> {
                                binding.buttonContinue.setEnabled(isChecked);
                                binding.buttonContinue.setText(isChecked ? "Finish" : "Continue");
                            });

                            binding.buttonContinue.setEnabled(fragment3.isCheckboxChecked());
                            binding.buttonContinue.setText(fragment3.isCheckboxChecked() ? "Finish" : "Continue");
                        });
                    } else {
                        Toast.makeText(this, "Please enter your name!", Toast.LENGTH_SHORT).show();
                    }
                }

            } else if (currentFragment == 3) {
                Toast.makeText(this, "Finished", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void SetBtnContinueEnabled() {
        binding.buttonContinue.setEnabled(true);
        binding.buttonContinue.setText("Continue");
    }

    @Override
    public void onContinueClicked(String name) {
        Toast.makeText(this, "Your Name is: " + name, Toast.LENGTH_SHORT).show();
    }
}
