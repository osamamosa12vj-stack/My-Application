package com.example.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.databinding.Fragment2Binding;

public class Fragment2 extends Fragment {

    private Fragment2Binding binding;
    private OnDataPassListener dataPassListener;

    public interface OnDataPassListener {
        void onDataPass(String name, String email, String age, String gender);
    }

    public static Fragment2 newInstance(String param1, String param2) {
        return new Fragment2();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof OnDataPassListener) {
            dataPassListener = (OnDataPassListener) context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        binding = Fragment2Binding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    public void sendData() {

        String name = binding.editText.getText().toString().trim();
        String email = binding.editTextEmail.getText().toString().trim();
        String age = binding.editTextAge.getText().toString().trim();
        String gender = binding.editTextGender.getText().toString().trim();

        if (TextUtils.isEmpty(name)) {
            binding.editText.setError("Enter name");
            return;
        }

        if (TextUtils.isEmpty(email)) {
            binding.editTextEmail.setError("Enter email");
            return;
        }

        if (TextUtils.isEmpty(age)) {
            binding.editTextAge.setError("Enter age");
            return;
        }

        if (TextUtils.isEmpty(gender)) {
            binding.editTextGender.setError("Enter gender");
            return;
        }

        if (dataPassListener != null) {
            dataPassListener.onDataPass(name, email, age, gender);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        dataPassListener = null;
    }
}