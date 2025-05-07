package com.example.localeventshub_project2cst_338;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertThrows;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.localeventshub_project2cst_338.database.LocalEventsDAO;
import com.example.localeventshub_project2cst_338.database.LocalEventsDatabase;
import com.example.localeventshub_project2cst_338.database.UserDAO;
import com.example.localeventshub_project2cst_338.database.entities.LocalEvents;
import com.example.localeventshub_project2cst_338.database.entities.User;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.List;

@RunWith(AndroidJUnit4.class)
public class LocalEventsDatabaseUnitTest {
    private LocalEventsDAO localEventsDAO;
    private UserDAO userDAO;
    private LocalEventsDatabase db;

    @Before
    public void createDB(){
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, LocalEventsDatabase.class).build();
        localEventsDAO = db.getLocalEventsDAO();
        userDAO = db.getuserDAO();
    }

    @After
    public void closeDB() throws IOException {
        db.close();
    }

    @Test
    public void insertEvent() throws Exception{
        LocalEvents localEvent = new LocalEvents("Example Event", 3, "A fair");
        localEventsDAO.insert(localEvent);
        LocalEvents byEvent = localEventsDAO.getEventByName("Example Event");
        assertThat(byEvent, equalTo(localEvent));
    }

    @Test
    public void updateEvent() throws Exception{
        LocalEvents testEvent = new LocalEvents("Another event", 7, "A concert");
        localEventsDAO.insert(testEvent);
        List<LocalEvents> byEvent = localEventsDAO.getAllRecords();
        assertThat(byEvent.size(), is(1));
    }

    @Test
    public void deleteEvent() throws Exception{
        localEventsDAO.insert(new LocalEvents("Something cool", 8, "A convention"));
        localEventsDAO.deleteAll();
        assertThat(localEventsDAO.getAllRecords().size(), is(0));
    }

//    @Test
//    public void insertUser() throws Exception{
//        User user = new User("testing", "testing", 93955);
//        userDAO.insert(user);
//        User byUser = userDAO.getUserByUsername("testing");
//        assertThat(byUser, equalTo(user));
//    }
//
//    @Test
//    public void updateUser() throws Exception{
//        User user = new User("anotherOne", "anotherOne", 93940);
//        userDAO.insert(user);
//    }
//
//    @Test
//    public void deleteUser() throws Exception{
//        userDAO.insert(new User("testing", "testing", 93955));
//        userDAO.deleteAll();
////        assertThat(userDAO.size(), is(0));
//    }
}
