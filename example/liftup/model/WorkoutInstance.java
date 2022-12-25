package com.example.liftup.model;

public class WorkoutInstance {
    private long workoutId;
    private long liftId;
    private int sets;
    private int reps;

    public WorkoutInstance() {
    }

    public WorkoutInstance(long workoutId, long liftId, int sets, int reps) {
        this.workoutId = workoutId;
        this.liftId = liftId;
        this.sets = sets;
        this.reps = reps;
    }

    public long getWorkoutId() {
        return workoutId;
    }
    public long getLiftId() {
        return liftId;
    }
    public int getSets() {
        return sets;
    }
    public int getReps() {
        return reps;
    }


    public void setWorkoutId(long workoutId) {
        this.workoutId = workoutId;
    }

    public void setLiftId(long liftId) {
        this.liftId = liftId;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

}