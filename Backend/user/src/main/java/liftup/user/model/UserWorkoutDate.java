package liftup.user.model;

import javax.persistence.*;

@Entity
@IdClass(UserDateId.class)
public class UserWorkoutDate {
    @Id
    private String username;
    @Id
    private String date;
    private int workoutId;
    private boolean completed;

    public UserWorkoutDate() {
    }

    public UserWorkoutDate(String username, String date, int workoutId, boolean completed) {
        this.username = username;
        this.date = date;
        this.workoutId = workoutId;
        this.completed = completed;
    }

    public String getUsername() {
        return username;
    }
    public String getDate() {
        return date;
    }
    public int getWorkoutId() {
        return workoutId;
    }
    public boolean isCompleted() {
        return completed;
    }
}
