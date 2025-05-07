package com.example.localeventshub_project2cst_338;

import static com.example.localeventshub_project2cst_338.MainActivity.TAG;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import com.example.localeventshub_project2cst_338.database.LocalEventsRepository;
import com.example.localeventshub_project2cst_338.database.UserDAO;
import com.example.localeventshub_project2cst_338.database.entities.User;
import com.example.localeventshub_project2cst_338.databinding.ActivityCreateAccountBinding;

import java.util.concurrent.Executors;


public class CreateAccount extends AppCompatActivity {

    private ActivityCreateAccountBinding binding;
    private LocalEventsRepository repository;
    private UserDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreateAccountBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.CreateAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyUser();
            }
        });

    }

    private void verifyUser() {
        String username = binding.CreateUsernameEditText.getText().toString();
        String password = binding.CreatePasswordEditText.getText().toString();
        int zipcode = Integer.parseInt(binding.ZipCodeInputEditText.getText().toString());
        if (username.isEmpty()) {
            toastMaker("Username can not be empty");
            return;
        } else if (repository.getUserByUserName(username).toString().equals(username)) {
            toastMaker("Username already taken");
            return;
        }

        User newUser = new User(username, password, zipcode);
        dao.insert(newUser);
        Log.d(TAG, "Account created successfully");
        startActivity(LoginActivity.loginIntentFactory(getApplicationContext()));
    }

    private void toastMaker(String message) {
        Toast.makeText(CreateAccount.this, message, Toast.LENGTH_SHORT).show();
    }

    static Intent createAccountIntentFactory(Context context) {
        return new Intent(context, CreateAccount.class);
    }
}