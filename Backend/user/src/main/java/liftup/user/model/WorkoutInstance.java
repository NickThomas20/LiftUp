package liftup.user.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@IdClass(WorkoutInstanceId.class)
public class WorkoutInstance {
    @Id
    private long workoutId;
    @Id
    private long liftId;
    private int sets;
    private int reps;
    private String exercise;

    public WorkoutInstance() {
    }

    public WorkoutInstance(long workoutId, long liftId, int sets, int reps, String exercise) {
        this.workoutId = workoutId;
        this.liftId = liftId;
        this.sets = sets;
        this.reps = reps;
        this.exercise = exercise;
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

    public String getExercise() {
        return exercise;
    }

    public void setExercise(String exercise) {
        this.exercise = exercise;
    }
}
