package com.example.localeventshub_project2cst_338.database;

import androidx.lifecycle.LiveData;

import com.example.localeventshub_project2cst_338.database.entities.User;

public class LocalEventsRepository {

    private final LocalEventsDAO localEventsDAO;
    private final UserDAO userDAO;

    public LocalEventsRepository(LocalEventsDAO localEventsDAO, UserDAO userDAO) {
        this.localEventsDAO = localEventsDAO;
        this.userDAO = userDAO;
    }

    public LiveData<User> getUserByUserName(String username) {
        return userDAO.getUserByUserName(username);
    }
}
