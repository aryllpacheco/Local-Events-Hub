package com.example.localeventshub_project2cst_338;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.util.ArrayList;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarViewHolder> {

    private final ArrayList<String> daysOfMonth;
    private final ArrayList<DateInfo> dates;
    private final LocalDate monthDate;
    private final OnItemListener onItemListener;

    public CalendarAdapter(ArrayList<String> daysOfMonth, ArrayList<DateInfo> dates, LocalDate monthDate, OnItemListener onItemListener) {
        this.daysOfMonth = daysOfMonth;
        this.dates = dates;
        this.monthDate = monthDate;
        this.onItemListener = onItemListener;
    }

    @NonNull
    @Override
    public CalendarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.cells, parent, false);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = (int)(parent.getHeight() * 0.1666666);
        return new CalendarViewHolder(view, onItemListener);

        
    }
    public void updateEvent(int year, int month, int day, String event){
        if(isValid(year, month, day, event)){
            dates.add(new DateInfo(year, month, day, event));

        }

    }
    public boolean isValid(int year, int month, int day, String event){
        //not allowing to add events further than 2026
        if(year<2025||year>2026){
            return false;
        }
        if(month<1||month>12){
            return false;
        }
        //should update to make it so that the day matches the correct days in month
        if(day<0|| day>31){
            return false;
        }
        if(event.isEmpty()){
            return false;
        }
        return true;
    }
    @Override
    public void onBindViewHolder(@NonNull CalendarViewHolder holder, int position) {
        String day = daysOfMonth.get(position);
        holder.dayOfMonth.setText(day);

        if (!day.isEmpty()) {
            int dayInt = Integer.parseInt(day);
            int month = monthDate.getMonthValue();
            int year = monthDate.getYear();

            // Check for event matching this date
            String eventText = null;
            for (DateInfo d : dates) {
                if (d.day == dayInt && d.month == month && d.year == year) {
                    eventText = d.event;
                    break;
                }
            }

            if (eventText != null) {
                holder.setEvent(day + "\n" + eventText);
            } else {
                holder.setEvent(day); // No event, show only day
            }
        } else {
            holder.setEvent(""); // Empty cell
        }
    }

    @Override
    public int getItemCount() {
        return daysOfMonth.size();
    }

    public interface OnItemListener {
        void onItemClick(int position, String dayText);
    }
}
