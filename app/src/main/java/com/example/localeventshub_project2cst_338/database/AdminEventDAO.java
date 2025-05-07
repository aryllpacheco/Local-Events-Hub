package com.example.localeventshub_project2cst_338.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.localeventshub_project2cst_338.database.entities.AdminEvent;
import com.example.localeventshub_project2cst_338.database.entities.LocalEvents;

import java.util.List;

@Dao
public interface AdminEventDAO {

    @Query("DELETE from " + LocalEventsDatabase.ADMIN_EVENTS_TABLE)
    void deleteAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(AdminEvent... AdminEvent);

    @Query("SELECT * from " + LocalEventsDatabase.ADMIN_EVENTS_TABLE + " WHERE eventName = :eventName")
    List<LocalEvents> getEventByName(String eventName);

    @Query("SELECT * from " + LocalEventsDatabase.ADMIN_EVENTS_TABLE + " ORDER BY eventTime DESC")
    List<LocalEvents> getAllRecords();
}
