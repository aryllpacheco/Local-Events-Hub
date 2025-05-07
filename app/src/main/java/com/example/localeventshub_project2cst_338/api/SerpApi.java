package com.example.localeventshub_project2cst_338.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SerpApi {
    @GET("search.json")
    Call<ApiResponse> getEvents(
            @Query("engine") String engine,
            @Query("q") String query,
            @Query("gl") String gl,
            @Query("hl") String hl,
            @Query("api_key") String apiKey
    );
}


