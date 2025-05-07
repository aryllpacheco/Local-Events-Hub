package com.example.localeventshub_project2cst_338;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.localeventshub_project2cst_338.database.LocalEventsDAO;
import com.example.localeventshub_project2cst_338.database.LocalEventsDatabase;
import com.example.localeventshub_project2cst_338.database.entities.LocalEvents;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.List;

@RunWith(AndroidJUnit4.class)
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
        LocalEvents byEvent = dao.getEventByName("Example Event");
        assertThat(byEvent, equalTo(localEvent));
    }

    @Test
    public void update() throws Exception{
        LocalEvents testEvent = new LocalEvents("Another event", 7, "A concert");
        dao.insert(testEvent);
        List<LocalEvents> byEvent = dao.getAllRecords();
        assertThat(byEvent.size(), is(1));
    }

    @Test
    public void deleteEvent() throws Exception{
        dao.insert(new LocalEvents("Something cool", 8, "A convention"));
        dao.deleteAll();
        assertThat(dao.getAllRecords().size(), is(0));
    }
}
