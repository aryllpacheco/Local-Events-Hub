package com.example.localeventshub_project2cst_338;

import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;

import com.example.localeventshub_project2cst_338.databinding.ActivityDisplayEventsBinding;


public class DisplayEvents extends AppCompatActivity {

    //TODO: Create the display layout, will be able to scroll on the page
    private ActivityDisplayEventsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_events);
    }
}