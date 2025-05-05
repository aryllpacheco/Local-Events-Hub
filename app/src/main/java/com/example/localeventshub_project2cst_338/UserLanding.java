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
import com.example.localeventshub_project2cst_338.databinding.UserLandingBinding;


public class UserLanding extends AppCompatActivity {
    private UserLandingBinding binding;




    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = UserLandingBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_user_landing);




    }
    static Intent UserLandingIntentFactory(Context context){
        return new Intent(context, UserLanding.class);
    }


}
