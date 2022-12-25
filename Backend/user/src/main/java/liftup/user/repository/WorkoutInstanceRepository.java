package liftup.user.repository;

import liftup.user.model.WorkoutInstance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface WorkoutInstanceRepository extends JpaRepository<WorkoutInstance, Long> {
    List<WorkoutInstance> findAllByWorkoutId(long workoutId);
    List<WorkoutInstance> findAllByLiftId(long liftId);
    WorkoutInstance findByWorkoutIdAndLiftId(long workoutId, long liftId);

    @Modifying
    @Transactional
    @Query("delete from WorkoutInstance wi where wi.workoutId = ?1")
    void deleteAllByWorkoutId(long workoutId);

    @Modifying
    @Transactional
    @Query("delete from WorkoutInstance wi where wi.liftId = ?1")
    void deleteAllByLiftId(long liftId);
}
