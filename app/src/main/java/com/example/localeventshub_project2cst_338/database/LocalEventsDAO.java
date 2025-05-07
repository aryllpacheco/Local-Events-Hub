package com.example.localeventshub_project2cst_338.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.localeventshub_project2cst_338.database.entities.LocalEvents;

import java.util.List;

@Dao
public interface LocalEventsDAO {

    @Query("DELETE from " + LocalEventsDatabase.LOCAL_EVENTS_TABLE)
    void deleteAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(LocalEvents... localEvents);

    @Query("SELECT * from " + LocalEventsDatabase.LOCAL_EVENTS_TABLE + " WHERE eventName == :eventName")
    LocalEvents getEventByName(String eventName);

   @Query("SELECT * from " + LocalEventsDatabase.LOCAL_EVENTS_TABLE + " ORDER BY eventTime desc")
   List<LocalEvents> getAllRecords();
}
