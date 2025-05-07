package com.example.localeventshub_project2cst_338;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
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
        View view = binding.getRoot();
        setContentView(view);

        repository = LocalEventsRepository.getRepository(getApplication());

        binding.SignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyUser();
            }
        });
    }

    private void verifyUser(){
        final String TAG = "LOGIN_TAG_";
        String username = binding.UserNameInputStringEditText.getText().toString();
        if(username.isEmpty()){
            toastMaker("Username may not be blank");
            return;
        }
        LiveData<User> userObserver = repository.getUserByUserName(username);

        userObserver.observe(this, user -> {
            if(user != null){
                String password = binding.PasswordInputStringEditText.getText().toString();
                if(password.equals(user.getPassword())){
                    toastMaker("Password accepted!");
                    SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(MainActivity.SHARED_PREFERENCE_USERID_KEY, Context.MODE_PRIVATE);
                    SharedPreferences.Editor sharedPrefEditor = sharedPreferences.edit();
                    sharedPrefEditor.putInt(MainActivity.SHARED_PREFERENCE_USERID_KEY, user.getId());
                    sharedPrefEditor.apply();
                    if(user.isAdmin()){
                        //Admin landing page start activity
                        Log.d(TAG, "Admin login successful");
                        startActivity(AdminLandingPage.AdminLoginIntentFactory(getApplicationContext()));
                    }else {
                        //User landing page start activity
                        Log.d(TAG, "User login successful");

                        startActivity(UserLandingPage.UserLoginIntentFactory(getApplicationContext()));
                    }

                }else{
                    toastMaker("Invalid password");
                    binding.PasswordInputStringEditText.setSelection(0);
                }
            }else{
                toastMaker(String.format("%s is not a valid username", username));
                binding.UserNameInputStringEditText.setSelection(0);
            }
        });
    }

    private void toastMaker(String message) {
        Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    static Intent loginIntentFactory(Context context){
        return new Intent(context, LoginActivity.class);
    }
}