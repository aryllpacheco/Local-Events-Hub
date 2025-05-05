package com.example.localeventshub_project2cst_338;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import androidx.appcompat.app.AppCompatActivity;

import com.example.localeventshub_project2cst_338.databinding.ActivityDisplayEventsBinding;


public class DisplayEvents extends AppCompatActivity {

    private ActivityDisplayEventsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDisplayEventsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }

    static Intent displayEventsIntentFactory(Context context){
        return new Intent(context, DisplayEvents.class);
    }
}