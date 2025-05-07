package com.example.localeventshub_project2cst_338;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.localeventshub_project2cst_338.databinding.CalendarBinding;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class Calendar extends AppCompatActivity implements CalendarAdapter.OnItemListener {



    //Fake event



    private CalendarBinding binding;
    private TextView monthYearText;
    private RecyclerView calendarRecyclerView;
    private LocalDate selectedDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        binding = CalendarBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        initWidgets();
        selectedDate = LocalDate.now();
        setMonthView();




    }

    private void setMonthView() {
        monthYearText.setText(monthYearFromDate(selectedDate));
        ArrayList<String> daysInMonth = daysInMonthArray(selectedDate);


        ArrayList<DateInfo> dates = new ArrayList<>();
        dates.add(new DateInfo(2025, 5, 7, "parade"));
        dates.add(new DateInfo(2025,5,19,"walking"));
        CalendarAdapter calendarAdapter = new CalendarAdapter(daysInMonth,dates,selectedDate,this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),7);
        calendarRecyclerView.setLayoutManager(layoutManager);
        calendarRecyclerView.setAdapter(calendarAdapter);
    }

    private ArrayList<String> daysInMonthArray(LocalDate date) {
        ArrayList<String> daysInMonthArray = new ArrayList<>();
        YearMonth yearMonth = YearMonth.from(date);
        int daysInMonth = yearMonth.lengthOfMonth();
        LocalDate firstOfMonth = selectedDate.withDayOfMonth(1);
        int dayOfWeek = firstOfMonth.getDayOfWeek().getValue();
        for(int i = 1; i<=42;i++){
            if(i<dayOfWeek||i>daysInMonth+dayOfWeek){
                daysInMonthArray.add("");
            }else{
                daysInMonthArray.add(String.valueOf(i-dayOfWeek));
            }
        }

        return daysInMonthArray;
    }

    private String monthYearFromDate(LocalDate date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
        return date.format(formatter);
    }

    private void initWidgets() {
        calendarRecyclerView = findViewById(R.id.calendarRecyclerView);
        monthYearText = findViewById(R.id.MonthOfYear);
    }

    ;

    public void previousMonth(View view) {
        selectedDate = selectedDate.minusMonths(1);
        setMonthView();
    }

    public void nextMonth(View view) {
        selectedDate = selectedDate.plusMonths(1);
        setMonthView();
    }

    @Override
    public void onItemClick(int position, String dayText) {
        if(dayText.equals("")){
            String message = "Selected date: " + dayText + " " + monthYearFromDate(selectedDate);
            Toast.makeText(this,message,Toast.LENGTH_LONG).show();
        }
    }

    public void prev(View view) {
        startActivity(UserLandingPage.UserLandingIntentFactory(getApplicationContext()));

    }


    static Intent CalendarFactory(Context context){
        return new Intent(context, Calendar.class);
    }


}