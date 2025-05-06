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

    @Query("SELECT * from " + LocalEventsDatabase.USER_TABLE + " WHERE id == :userId")
    LiveData<User> getUserByUserId(int userId);

    @Query("SELECT * from " + LocalEventsDatabase.USER_TABLE + " WHERE isAdmin = 1 LIMIT 1")
    User getAdminUser();

    @Query("SELECT * from " + LocalEventsDatabase.USER_TABLE + " WHERE username = :username LIMIT 1")
    User getUserByUsername(String username);

}
