package liftup.user.model;

import javax.persistence.*;

@Entity
public class Workout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long workoutId;
    private String author;
    private String creationDate;

    public Workout() {
    }

    public Workout(long workoutId, String author, String creationDate) {
        this.workoutId = workoutId;
        this.author = author;
        this.creationDate = creationDate;
    }

    public long getWorkoutId() {
        return workoutId;
    }

    public void setWorkoutId(int workoutId) {
        this.workoutId = workoutId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }
}
