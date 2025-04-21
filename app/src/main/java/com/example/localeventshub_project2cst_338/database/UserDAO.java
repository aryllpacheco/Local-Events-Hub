package com.example.localeventshub_project2cst_338.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.localeventshub_project2cst_338.database.entities.User;

@Dao
public interface UserDAO {

    @Query("DELETE from " + LocalEventsDatabase.USER_TABLE)
    void deleteAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(User... user);

   @Query("SELECT * from " + LocalEventsDatabase.USER_TABLE + " WHERE username == :username")
   LiveData<User> getUserByUserName(String username);
}
