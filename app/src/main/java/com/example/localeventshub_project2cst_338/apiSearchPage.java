package com.example.localeventshub_project2cst_338;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


import com.example.localeventshub_project2cst_338.api.ApiResponse;
import com.example.localeventshub_project2cst_338.api.Event;
import com.example.localeventshub_project2cst_338.api.SerpApi;
import com.example.localeventshub_project2cst_338.databinding.ActivityApiSearchPageBinding;

import java.util.List;

public class apiSearchPage extends AppCompatActivity {

    private ActivityApiSearchPageBinding binding;
    private SerpApi serpApi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityApiSearchPageBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        //Setting up API
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://serpapi.com/").addConverterFactory(GsonConverterFactory.create()).build();
        serpApi = retrofit.create(SerpApi.class);

        binding.searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String search = binding.searchBar.getText().toString();
                if (!search.isEmpty()) {
                    apiCall(search);
                }
            }
        });
    }

    private void apiCall(String search) {
        String updatedSearch = search.replace(" ", "+");

        Call<ApiResponse> call = serpApi.getEvents(
                "google_events",
                updatedSearch,
                "us",
                "en",
                "90257afc0a4d22e217a699c485665124d10a74c6ccd12c80a902febf72ba47a8"
        );

        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful()) {
                    ApiResponse apiResponse = response.body();
                    if (apiResponse != null) {
                        List<Event> events = apiResponse.getEvents();
                        if (events != null && !events.isEmpty()) {
                            Log.d("API Success", "Events received: " + events.size());
                            EventAdapter adapter = new EventAdapter(events);
                            binding.recyclerView.setLayoutManager(new LinearLayoutManager(apiSearchPage.this));
                            binding.recyclerView.setAdapter(adapter);
                        } else {
                            Log.e("API Error", "Events list is null or empty");
                            Toast.makeText(apiSearchPage.this, "No events found for that search.", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Log.e("API Error", "Response body is null");
                        Toast.makeText(apiSearchPage.this, "No response from server.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Log.e("API Error", "Unsuccessful response: " + response.code());
                    Toast.makeText(apiSearchPage.this, "Error fetching data.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Log.e("API Failure", "Network or conversion error: " + t.getMessage(), t);
                Toast.makeText(apiSearchPage.this, "Failed to connect to API.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

