package com.example.localeventshub_project2cst_338;

public class DateInfo {
    public int year;
    public int month;
    public int day;
    public String event;

    public DateInfo(int year, int month, int day, String event) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.event = event;
    }

    public boolean matches(int year, int month, int day, String event) {
        return this.year == year && this.month == month && this.day == day && this.event.equals(event);
    }
}

