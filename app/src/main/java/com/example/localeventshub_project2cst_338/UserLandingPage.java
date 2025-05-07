package com.example.localeventshub_project2cst_338;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.localeventshub_project2cst_338.databinding.ActivityUserLandingPageBinding;

public class UserLandingPage extends AppCompatActivity {
    private ActivityUserLandingPageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityUserLandingPageBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        super.onCreate(savedInstanceState);
        binding.navigatetoAPI.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(UserLandingPage.this, apiSearchPage.class);
                startActivity(intent);
            }
        });
    }


    static Intent UserLoginIntentFactory(Context context) {
        return new Intent(context, UserLandingPage.class);

    }
}