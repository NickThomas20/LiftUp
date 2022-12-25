package liftup.user.model;

import javax.persistence.*;

@Entity
public class Lifts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long liftID;
    private String exercise;

    public Lifts() {
    }

    public Lifts(String exercise) {
        this.exercise = exercise;
    }

    public long getLiftID() {
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
}
