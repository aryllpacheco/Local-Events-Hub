package com.example.localeventshub_project2cst_338;

import static org.junit.Assert.assertEquals;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;

import com.example.localeventshub_project2cst_338.database.LocalEventsDAO;
import com.example.localeventshub_project2cst_338.database.LocalEventsDatabase;
import com.example.localeventshub_project2cst_338.database.entities.LocalEvents;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class LocalEventsDatabaseUnitTest {
    private LocalEventsDAO dao;
    private LocalEventsDatabase db;

    @Before
    public void createDB(){
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, LocalEventsDatabase.class).build();
        dao = db.getLocalEventsDAO();
    }

    @After
    public void closeDB() throws IOException {
        db.close();
    }

    @Test
    public void insertEvent() throws Exception{
        LocalEvents localEvent = new LocalEvents("Example Event", 3, "A fair");
        dao.insert(localEvent);
        LiveData<LocalEvents> byEvent = dao.getEventByName("Example Event");
        assertEquals(byEvent.getValue(), localEvent);
    }

    @Test
    public void update() throws Exception{

    }

    @Test
    public void deleteEvent() throws Exception{
        dao.deleteAll();
//        assertEquals();
    }
}