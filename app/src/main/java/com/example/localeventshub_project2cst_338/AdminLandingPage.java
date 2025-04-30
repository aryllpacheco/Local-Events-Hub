package com.example.localeventshub_project2cst_338;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import com.example.localeventshub_project2cst_338.databinding.ActivityAdminLandingPageBinding;
import com.example.localeventshub_project2cst_338.databinding.ActivityMainBinding;

public class AdminLandingPage extends AppCompatActivity {
    private ActivityAdminLandingPageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityAdminLandingPageBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        super.onCreate(savedInstanceState);
    }
    static Intent AdminLoginIntentFactory(Context context, int user_ID){
        return new Intent(context, LoginActivity.class);
    }
}