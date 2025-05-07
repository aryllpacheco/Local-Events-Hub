package com.example.localeventshub_project2cst_338.api;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ApiResponse {
    @SerializedName("events_results")
    private List<Event> events;

    public List<Event> getEvents() {
        return events;
    }
}


