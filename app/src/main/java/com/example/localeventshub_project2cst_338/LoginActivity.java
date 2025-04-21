package com.example.localeventshub_project2cst_338;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import com.example.localeventshub_project2cst_338.database.LocalEventsRepository;
import com.example.localeventshub_project2cst_338.database.entities.User;
import com.example.localeventshub_project2cst_338.databinding.ActivityLoginBinding;



public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private LocalEventsRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_login);

        repository = LocalEventsRepository.getRepository(getApplication());

        binding.SignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyUser();
            }
        });
    }

    private void verifyUser(){
        String username = binding.UserNameInputStringEditText.getText().toString();
        if(username.isEmpty()){
            Toast.makeText(getApplicationContext(), "Can't username leave empty",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        LiveData<User> userObserver = repository.getUserByUserName(username);
        userObserver.observe(this, user -> {
            if(user != null){
                String password = binding.PasswordInputStringEditText.getText().toString();
                if(password.equals(user.getPassword())){
                    SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(MainActivity.SHARED_PREFERENCE_USERID_KEY, Context.MODE_PRIVATE);
                    SharedPreferences.Editor sharedPrefEditor = sharedPreferences.edit();
                    sharedPrefEditor.putInt(MainActivity.SHARED_PREFERENCE_USERID_KEY, user.getId());
                    sharedPrefEditor.apply();
                    if(user.isAdmin()){
                        //Admin landing page start activity
                        startActivity(MainActivity.mainActivityIntentFactory(getApplicationContext(), user.getId()));
                    }else{
                        //User landing page start activity
                        startActivity(MainActivity.mainActivityIntentFactory(getApplicationContext(), user.getId()));
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Invalid password",
                            Toast.LENGTH_SHORT).show();
                    binding.PasswordInputStringEditText.setSelection(0);
                }
            }else{
                Toast.makeText(getApplicationContext(), String.format("%s is not a valid " +
                        "username", username), Toast.LENGTH_SHORT).show();
                binding.UserNameInputStringEditText.setSelection(0);
            }
        });
    }

    static Intent loginIntentFactory(Context context){
        return new Intent(context, LoginActivity.class);
    }
}