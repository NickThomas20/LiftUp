package com.example.liftup.model;

/**
 * Holds data for a lift that the user can view.
 */
public class Lift {

    private int liftID;
    private String exercise;
    private int reps;

    public Lift() {
    }

    public Lift(String exercise) {
        this.exercise = exercise;
    }

    public Lift(String exercise, int reps) {
        this.exercise = exercise;
        this.reps = reps;
    }

    public int getLiftID() {
        return liftID;
    }

    public void setLiftID(int liftID) {
        this.liftID = liftID;
    }

    public String getExercise() {
        return exercise;
    }

    public void setExercise(String exercise) {
        this.exercise = exercise;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }


}
