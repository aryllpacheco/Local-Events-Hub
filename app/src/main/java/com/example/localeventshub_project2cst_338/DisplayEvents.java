package com.example.localeventshub_project2cst_338;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import androidx.appcompat.app.AppCompatActivity;

import com.example.localeventshub_project2cst_338.database.LocalEventsRepository;
import com.example.localeventshub_project2cst_338.database.entities.LocalEvents;
import com.example.localeventshub_project2cst_338.databinding.ActivityDisplayEventsBinding;

import java.util.ArrayList;


public class DisplayEvents extends AppCompatActivity {

    private ActivityDisplayEventsBinding binding;
    private LocalEventsRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDisplayEventsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        updateDisplay();
    }

    private void updateDisplay() {
        ArrayList<LocalEvents> allLogs = repository.getAllEvents();
        if (allLogs.isEmpty()) {
            binding.CurrentEventDisplayTextView.setText("No events currently on the books");
        }
        StringBuilder sb = new StringBuilder();
        for (LocalEvents log : allLogs) {
            sb.append(log);
        }
        binding.CurrentEventDisplayTextView.setText(sb.toString());
    }
    static Intent displayEventsIntentFactory(Context context){
        return new Intent(context, DisplayEvents.class);
    }
}