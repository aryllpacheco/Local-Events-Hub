package com.example.localeventshub_project2cst_338.database;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.localeventshub_project2cst_338.MainActivity;
import com.example.localeventshub_project2cst_338.database.entities.LocalEvents;
import com.example.localeventshub_project2cst_338.database.entities.User;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class LocalEventsRepository {

    private final LocalEventsDAO localEventsDAO;
    private final UserDAO userDAO;
    private static LocalEventsRepository repository;

    public LocalEventsRepository(Application application) {
        LocalEventsDatabase db = LocalEventsDatabase.getDatabase(application);
        this.localEventsDAO = db.getLocalEventsDAO();
        this.userDAO = db.getuserDAO();
    }

    public static LocalEventsRepository getRepository(Application application) {
        if (repository != null) {
            return repository;
        } else {
            Log.e(MainActivity.TAG, "Repository is null. Initialization failed.");
        }
        Future<LocalEventsRepository> future = LocalEventsDatabase.databaseWriteExecutor.submit(() -> new LocalEventsRepository(application));

        try {
            repository = future.get();
        } catch (InterruptedException | ExecutionException e) {
            Log.d(MainActivity.TAG, "Problem getting LocalEventRepository, thread error");
        }

        return repository;
    }

    public LiveData<User> getUserByUserName(String username) {
        return userDAO.getUserByUserName(username);
    }

    public LiveData<LocalEvents> getEventByName(String eventName) {
        return localEventsDAO.getEventByName(eventName);
    }

    public void insertUser(User user){
        LocalEventsDatabase.databaseWriteExecutor.execute(()->{
            userDAO.insert(user);
        });
    }

    public void insertEvent(LocalEvents event){
        LocalEventsDatabase.databaseWriteExecutor.execute(()->{
            localEventsDAO.insert(event);
        });
    }

    public LiveData<User> getUserByUserId(int userId) {
        return userDAO.getUserByUserId(userId);
    }

}
