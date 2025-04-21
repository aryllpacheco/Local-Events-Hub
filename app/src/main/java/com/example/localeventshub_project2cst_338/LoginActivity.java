package com.example.localeventshub_project2cst_338;

import android.os.Bundle;
import android.view.View;


import androidx.appcompat.app.AppCompatActivity;

import com.example.localeventshub_project2cst_338.databinding.ActivityLoginBinding;



public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.SignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //take the user to the landing page that corresponds
                //to either user or admin
            }
        });

    }
}