package com.example.localeventshub_project2cst_338.database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.localeventshub_project2cst_338.database.LocalEventsDatabase;

import java.time.LocalDate;
import java.util.Objects;

@Entity(tableName = LocalEventsDatabase.LOCAL_EVENTS_TABLE)
public class LocalEvents {

    @PrimaryKey
    private int id;
    private String eventName;
    private int eventTime;
    private String eventType;

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public int getEventTime() {
        return eventTime;
    }

    public void setEventTime(int eventTime) {
        this.eventTime = eventTime;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalEvents(String eventName, int eventTime, String eventType) {
        this.eventName = eventName;
        this.eventTime = eventTime;
        this.eventType = eventType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LocalEvents that = (LocalEvents) o;
        return id == that.id && Objects.equals(eventName, that.eventName) && Objects.equals(eventTime, that.eventTime) && Objects.equals(eventType, that.eventType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, eventName, eventTime, eventType);
    }
}
