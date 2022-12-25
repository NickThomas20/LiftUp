package liftup.user.repository;

import liftup.user.model.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import javax.transaction.Transactional;

public interface WorkoutRepository extends JpaRepository<Workout,Long> {
    Workout findByWorkoutId(long workoutId);

    @Modifying
    @Transactional
    @Query("delete from Workout w where w.author = ?1")
    void deleteWorkoutsBy(String author);

    @Modifying
    @Transactional
    @Query("update Workout w set w.author = ?1 where w.workoutId = ?2")
    void updateAuthor(String newAuthor, int workoutId);

    @Modifying
    @Transactional
    @Query("update Workout w set w.creationDate = ?1 where w.workoutId = ?2")
    void updateDate(String newdate, int workoutId);
}
