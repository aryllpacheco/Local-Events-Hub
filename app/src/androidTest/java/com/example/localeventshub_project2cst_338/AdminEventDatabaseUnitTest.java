package com.example.localeventshub_project2cst_338;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.localeventshub_project2cst_338.database.AdminEventDAO;
import com.example.localeventshub_project2cst_338.database.LocalEventsDatabase;
import com.example.localeventshub_project2cst_338.database.entities.AdminEvent;
import com.example.localeventshub_project2cst_338.database.entities.LocalEvents;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.List;

@RunWith(AndroidJUnit4.class)
public class AdminEventDatabaseUnitTest {
    private AdminEventDAO dao;
    private LocalEventsDatabase db;

    @Before
    public void createDB(){
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, LocalEventsDatabase.class).build();
        dao = db.adminEventDAO();
    }
    @After
    public void closeDB() throws IOException {
        db.close();
    }

    @Test
    public void insertEvent() throws Exception{
        AdminEvent testE1 = new AdminEvent("If you give an Otter a cookie", 1200, "school");
        dao.insert(testE1);
        assertThat(testE1.getEventName(), is(equalTo(testE1.getEventName())));
        List<LocalEvents> events = dao.getAllRecords();
        assertThat(events.size(), is(1));
    }
    @Test
    public void deleteEvent() throws Exception{
        dao.insert(new AdminEvent("Tesst", 1200, "Test"));
        dao.deleteAll();
        assertThat(dao.getAllRecords().size(), is(0));
    }
}

