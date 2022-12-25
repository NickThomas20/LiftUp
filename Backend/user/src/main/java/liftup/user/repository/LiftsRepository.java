package liftup.user.repository;

import liftup.user.model.Lifts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import javax.transaction.Transactional;
import java.util.List;

public interface LiftsRepository extends JpaRepository<Lifts, Long> {
    Lifts findByLiftID(long liftId);


    @Modifying
    @Transactional
    @Query("delete from Lifts l where l.exercise = ?1")
    void deleteLiftsBy(String exercise);

    @Modifying
    @Transactional
    @Query("delete from Lifts l where l.exercise = ?1")
    void clearLifts(String exercise);
}
