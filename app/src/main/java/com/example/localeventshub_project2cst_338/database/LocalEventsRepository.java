package com.example.localeventshub_project2cst_338.database;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.localeventshub_project2cst_338.MainActivity;
import com.example.localeventshub_project2cst_338.database.entities.LocalEvents;
import com.example.localeventshub_project2cst_338.database.entities.User;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class LocalEventsRepository {

    private final LocalEventsDAO localEventsDAO;
    private final UserDAO userDAO;
    private static LocalEventsRepository repository;
    private ArrayList<LocalEvents> allLogs;

    public LocalEventsRepository(Application application) {
        LocalEventsDatabase db = LocalEventsDatabase.getDatabase(application);
        this.localEventsDAO = db.getLocalEventsDAO();
        this.userDAO = db.getuserDAO();
    }

    public static LocalEventsRepository getRepository(Application application) {
        if (repository != null) {
            return repository;
        }
        Future<LocalEventsRepository> future = LocalEventsDatabase.databaseWriteExecutor.submit(new Callable<LocalEventsRepository>() {
            @Override
            public LocalEventsRepository call() throws Exception {
                return new LocalEventsRepository(application);
            }
        });


        try {
            repository = future.get();
        } catch (InterruptedException | ExecutionException e) {
            Log.d(MainActivity.TAG, "Problem getting LocalEventRepository, thread error");
        }

        return repository;
    }

    public ArrayList<LocalEvents> getAllEvents() {
        Future<ArrayList<LocalEvents>> future = LocalEventsDatabase.databaseWriteExecutor.submit(new Callable<ArrayList<LocalEvents>>() {
            @Override
            public ArrayList<LocalEvents> call() throws Exception {
                return (ArrayList<LocalEvents>) localEventsDAO.getAllRecords();
            }
        });
        try {
            return future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            Log.i(MainActivity.TAG, "Problem when getting all Local Events in the repository");
        }
        return null;
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
