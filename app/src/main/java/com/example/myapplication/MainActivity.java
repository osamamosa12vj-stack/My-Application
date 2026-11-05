package com.example.myapplication;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity
        implements Fragment2.OnDataPassListener {

    private ActivityMainBinding binding;

    private int currentFragment = 1;

    private String name;
    private String email;
    private String age;
    private String gender;

    private Fragment2 fragment2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Fragment1 fragment1 = Fragment1.newInstance(null, null);
        fragment2 = Fragment2.newInstance(null, null);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, fragment1)
                .commit();

        binding.buttonContinue.setOnClickListener(view -> {

            if (currentFragment == 1) {

                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, fragment2)
                        .addToBackStack(null)
                        .commit();

                currentFragment = 2;

            } else if (currentFragment == 2) {

                fragment2.sendData();
            }
        });
    }

    @Override
    public void onDataPass(
            String name,
            String email,
            String age,
            String gender) {

        this.name = name;
        this.email = email;
        this.age = age;
        this.gender = gender;

        Fragment3 fragment3 = Fragment3.newInstance(name);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment3)
                .addToBackStack(null)
                .commit();

        currentFragment = 3;

        binding.buttonContinue.setVisibility(android.view.View.GONE);
    }
}