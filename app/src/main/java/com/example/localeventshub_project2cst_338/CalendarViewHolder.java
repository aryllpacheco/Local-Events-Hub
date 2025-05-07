package com.example.localeventshub_project2cst_338;


import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CalendarViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public final TextView dayOfMonth;

    private final CalendarAdapter.OnItemListener onItemListener;
    public CalendarViewHolder(@NonNull View itemView, CalendarAdapter.OnItemListener onItemListener) {
        super(itemView);
        //C in change of what day it is
        dayOfMonth = itemView.findViewById(R.id.cell);
        this.onItemListener = onItemListener;
        itemView.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        onItemListener.onItemClick(getAdapterPosition(),(String)dayOfMonth.getText());
    }
    public void setEvent(String eventText) {
        dayOfMonth.setText(eventText);
    }

}
