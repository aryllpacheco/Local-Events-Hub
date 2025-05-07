package com.example.localeventshub_project2cst_338;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.localeventshub_project2cst_338.databinding.ActivityUserLandingPageBinding;

public class UserLandingPage extends AppCompatActivity {
    private ActivityUserLandingPageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUserLandingPageBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        binding.navigatetoAPI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserLandingPage.this, apiSearchPage.class);
                startActivity(intent);
            }
        });
        binding.calendarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserLandingPage.this, Calendar.class);
                startActivity(intent);
            }
        });
        binding.LogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserLandingPage.this, LoginActivity.class);
                startActivity(intent);
                finish(); // Optional: prevent user from coming back via back button
            }
        });
    }


    static Intent UserLoginIntentFactory(Context context) {
        return new Intent(context, UserLandingPage.class);

    }
}