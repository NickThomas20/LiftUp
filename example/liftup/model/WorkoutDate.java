package com.example.liftup.model;

import android.util.Log;

import com.example.liftup.Utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Holds the information for workout data on a calendar.
 */
public class WorkoutDate extends DateBase {
    private String username;

    /**
     * The date that this workout takes place on.
     */
    private String date;

    /**
     * The ID of the workout that will be, was, or was supposed to be completed on the given date.
     */
    private int workoutId;

    /**
     * If the date is in the past, was this workout completed?
     */
    private boolean completed;

    public WorkoutDate(){
        username = "";
        workoutId = 0;
        date = "";
        completed = false;
    }

    public WorkoutDate(String username, Date date)
    {
        this.username = username;
        this.date = date.toString();
        workoutId = 0;
        completed = false;
    }

    public WorkoutDate(String username, Date date, int workoutId, boolean isCompleted){
        this.username = username;
        this.date = date.toString();
        this.workoutId = workoutId;
        completed = isCompleted;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Date getDateAsDate() {
        return parseDate(date);
    }

    public String getDate()
    {
        return date;
    }

    public void setDate(Date date) {
        this.date = date.toString();
    }

    public int getWorkoutId() {
        return workoutId;
    }

    public void setWorkoutId(int workoutId) {
        this.workoutId = workoutId;
    }

    @Override
    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed)
    {
        this.completed = completed;
    }

    public static Date parseDate(String date)
    {
        SimpleDateFormat parser = new SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy");
        Date d;
        try
        {
            d = parser.parse(date);
        } catch (ParseException ex)
        {
            d = null;
        }

        return d;
    }
}
