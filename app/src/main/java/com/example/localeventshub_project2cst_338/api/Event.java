package com.example.localeventshub_project2cst_338.api;

import java.util.List;

public class Event {
    private String title;
    private DateInfo date;  // DateInfo class to handle date structure
    private List<String> address;  // Address is now a List of Strings

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public DateInfo getDate() {
        return date;
    }

    public List<String> getAddress() {
        return address;
    }

    // Method to get the full address as a single string
    public String getFullAddress() {
        if (address != null && !address.isEmpty()) {
            return String.join(", ", address);  // Join the array elements with a comma
        }
        return "No address available";
    }

    public String getStartDate() {
        return date != null ? date.getStartDate() : "No date available";  // Access start_date within date
    }
}


