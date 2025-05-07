package com.example.localeventshub_project2cst_338;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import androidx.lifecycle.LiveData;

import com.example.localeventshub_project2cst_338.database.entities.LocalEvents;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.io.IOException;
import java.util.ArrayList;


public class CalendarUnitTest {

    @Test
    public void incorrectDate() throws Exception{


        assertTrue(isValid(2025,11,19,"k"));
        assertTrue(isValid(2025,2,1,"Walking"));
        assertTrue(isValid(2025,11,16,"dog"));


        //checking if false values work
        assertFalse(isValid(2010,11,34,""));
        assertFalse(isValid(2025,11,34,"coding"));
        assertFalse(isValid(1904,11,34,"walk"));


    }

    //method within Calendar
    public boolean isValid(int year, int month, int day, String event){
        //not allowing to add events further than 2026
        if(year<2025||year>2026){
            return false;
        }
        if(month<1||month>12){
            return false;
        }
        if(day<0|| day>31){
            return false;
        }
        if(event.isEmpty()){
            return false;
        }
        return true;
    }

}
