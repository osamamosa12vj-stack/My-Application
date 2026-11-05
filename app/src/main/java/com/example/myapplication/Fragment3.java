
        package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment3 extends Fragment {

    private static final String ARG_NAME = "name";

    private TextView textViewWelcome;
    private CheckBox checkBoxAgree;
    private Button buttonFinish;

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

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_3, container, false);

        textViewWelcome = view.findViewById(R.id.textViewWelcome);
        checkBoxAgree = view.findViewById(R.id.checkBoxAgree);
        buttonFinish = view.findViewById(R.id.buttonFinish);

        String name = "";

        if (getArguments() != null) {
            name = getArguments().getString(ARG_NAME, "");
        }

        textViewWelcome.setText("Welcome, " + name + "!");

        buttonFinish.setEnabled(false);
        buttonFinish.setText("Continue");

        checkBoxAgree.setOnCheckedChangeListener((buttonView, isChecked) -> {

            buttonFinish.setEnabled(isChecked);

            if (isChecked) {
                buttonFinish.setText("Finish");
            } else {
                buttonFinish.setText("Continue");
            }
        });

        buttonFinish.setOnClickListener(v -> {
            if (checkBoxAgree.isChecked()) {
                buttonFinish.setText("Finished!");
            }
        });

        return view;
    }
}
