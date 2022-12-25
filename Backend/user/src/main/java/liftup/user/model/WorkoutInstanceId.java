package liftup.user.model;

import java.io.Serializable;
import java.util.Objects;

public class WorkoutInstanceId implements Serializable {
    private long workoutId;
    private long liftId;

    public WorkoutInstanceId() {
    }

    public WorkoutInstanceId(long workoutId, long liftId) {
        this.workoutId = workoutId;
        this.liftId = liftId;
    }

    public long getWorkoutId() {
        return workoutId;
    }

    public long getLiftId() {
        return liftId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WorkoutInstanceId)) return false;
        WorkoutInstanceId that = (WorkoutInstanceId) o;
        return getWorkoutId() == that.getWorkoutId() && getLiftId() == that.getLiftId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getWorkoutId(), getLiftId());
    }
}
