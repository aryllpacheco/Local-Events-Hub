package com.example.localeventshub_project2cst_338;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.localeventshub_project2cst_338.database.AdminEventDAO;
import com.example.localeventshub_project2cst_338.database.LocalEventsDatabase;
import com.example.localeventshub_project2cst_338.database.entities.AdminEvent;
import com.example.localeventshub_project2cst_338.databinding.ActivityEventAddingPageBinding;

public class EventAddingPage extends AppCompatActivity {
    private ActivityEventAddingPageBinding binding;
    private AdminEventDAO dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEventAddingPageBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        dao = LocalEventsDatabase.getDatabase(getApplicationContext()).adminEventDAO();


        binding.submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String eventName = binding.eventNameTextBox.getText().toString();
                String eventTime = binding.eventTimeTextBox.getText().toString();
                String eventType = binding.eventTypeTextBox.getText().toString();

                if(eventName.isEmpty() || eventTime.isEmpty() || eventType.isEmpty()) {
                    toastMaker("Field Can NOT be blank");
                }
                try{
                    int time = Integer.parseInt(eventTime);
                    AdminEvent newEvent = new AdminEvent(eventName, time, eventType);
                    LocalEventsDatabase.databaseWriteExecutor.execute(new Runnable() {
                        @Override
                        public void run() {
                            dao.insert(newEvent);
                        }
                    });

                }catch (NumberFormatException e) {
                    toastMaker("Time is not a int");
                }
                toastMaker("SUCCESS");

            }
        });
    }
    private void toastMaker(String message) {
        Toast.makeText(EventAddingPage.this, message, Toast.LENGTH_SHORT).show();
    }
}