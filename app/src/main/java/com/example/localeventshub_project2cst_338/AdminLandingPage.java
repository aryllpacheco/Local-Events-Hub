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

        binding.LogINOUTBUTTON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminLandingPage.this, MainActivity.class);
                startActivity(intent);
            }
        });



    }


    static Intent AdminLoginIntentFactory(Context context){
        return new Intent(context, AdminLandingPage.class);
    }
}