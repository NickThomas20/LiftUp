package com.example.liftup.model;

import com.example.liftup.Session;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Holds the data for a user created workout.
 */
public class Workout {
    private long workoutId;


    private String author;
    private String date;

    private List<Lift> lifts;

    public Workout(long workoutId, String author, String date) {
        this.workoutId = workoutId;
        this.author = author;
        this.date = date;
    }

    public Workout(){

    }

    public long getWorkoutId() {
        return workoutId;
    }

    public void setWorkoutId(long workoutId) {
        this.workoutId = workoutId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return String.format("Workout on %s by %s.", date, author);
    }
}
