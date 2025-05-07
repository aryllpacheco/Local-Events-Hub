package com.example.localeventshub_project2cst_338.database;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.localeventshub_project2cst_338.MainActivity;
import com.example.localeventshub_project2cst_338.database.entities.AdminEvent;
import com.example.localeventshub_project2cst_338.database.entities.LocalEvents;
import com.example.localeventshub_project2cst_338.database.entities.User;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {User.class, LocalEvents.class, AdminEvent.class}, version = 5, exportSchema = false)
public abstract class LocalEventsDatabase extends RoomDatabase {
    public static final String USER_TABLE = "usertable";
    public static final String LOCAL_EVENTS_TABLE = "localeventstable";
    public static final String ADMIN_EVENTS_TABLE = "admineventstable";
    private static final int NUMBER_OF_THREADS = 4;
    private static final String DATABASE_NAME = "LocalEventsDatabase";
    private static volatile LocalEventsDatabase INSTANCE;
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static LocalEventsDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (LocalEventsDatabase.class){
                if(INSTANCE == null){
                    Log.d(MainActivity.TAG, "Initializing LocalEventsDatabase...");
                    INSTANCE = Room.databaseBuilder(
                                    context.getApplicationContext(),
                                    LocalEventsDatabase.class,
                                    DATABASE_NAME
                            )
                            .fallbackToDestructiveMigration()
                            .addCallback(addDefaultValues)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static final RoomDatabase.Callback addDefaultValues = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db){
            super.onCreate(db);
            Log.i(MainActivity.TAG, "Database created");
            databaseWriteExecutor.execute(()-> {
                UserDAO dao = INSTANCE.getuserDAO();
                dao.deleteAll();
                User admin = new User("admin", "admin", 93940);
                admin.setAdmin(true);
                dao.insert(admin);
                User testUser1 = new User("testuser1", "testuser1", 93940);
                dao.insert(testUser1);
                LocalEventsDAO dao1 = INSTANCE.getLocalEventsDAO();
                dao1.deleteAll();
                LocalEvents event1 = new LocalEvents("Ren Faire", 7, "Fair");
                dao1.insert(event1);
                LocalEvents event2 = new LocalEvents("Symphony", 12, "Concert");
                dao1.insert(event2);
            });
        }
    };




    public abstract UserDAO getuserDAO();
    public abstract LocalEventsDAO getLocalEventsDAO();
    public abstract AdminEventDAO adminEventDAO();

}

