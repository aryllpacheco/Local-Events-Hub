package com.example.localeventshub_project2cst_338;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.localeventshub_project2cst_338.databinding.ActivityEventAddingPageBinding;

public class EventAddingPage extends AppCompatActivity {
    private ActivityEventAddingPageBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEventAddingPageBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }
}