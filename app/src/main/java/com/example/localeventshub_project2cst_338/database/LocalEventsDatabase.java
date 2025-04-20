package com.example.localeventshub_project2cst_338.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;


import com.example.localeventshub_project2cst_338.database.entities.User;

@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class LocalEventsDatabase extends RoomDatabase{
    public static final String USER_TABLE = "usertable";
    public static final String LOCAL_EVENTS_TABLE = "localeventstable";


    public abstract UserDAO getuserDAO();
    public abstract LocalEventsDAO getLocalEventsDAO();

}
