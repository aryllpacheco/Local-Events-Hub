package com.example.localeventshub_project2cst_338;

import static com.example.localeventshub_project2cst_338.LoginActivity.loginIntentFactory;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import com.example.localeventshub_project2cst_338.database.LocalEventsRepository;
import com.example.localeventshub_project2cst_338.database.UserDAO;
import com.example.localeventshub_project2cst_338.database.entities.User;
import com.example.localeventshub_project2cst_338.databinding.ActivityCreateAccountBinding;


public class CreateAccount extends AppCompatActivity {

    private ActivityCreateAccountBinding binding;
    private LocalEventsRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreateAccountBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_create_account);

        binding.CreateAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyUser();
            }
        });

    }

    private void verifyUser(){
        String username = binding.CreateUsernameEditText.getText().toString();
        String password = binding.CreatePasswordEditText.getText().toString();
        if(username.isEmpty()){
            Toast.makeText(getApplicationContext(), "Can't leave username empty",
                    Toast.LENGTH_SHORT).show();
            return;
        }else if(username.equals(repository.getUserByUserName(username))){
            Toast.makeText(getApplicationContext(), "Username already taken",
                    Toast.LENGTH_SHORT).show();
            return;
        }else{
//            getDatbase()
//            UserDAO dao = INSTANCE.getuserDao;
//            User user = new User(username, password);
//            insertUser(user);
//            loginIntentFactory(UserLandingActivity.class);
        }
    }

    static Intent createAccountIntentFactory(Context context){
        return new Intent(context, CreateAccount.class);
    }
}