package liftup.user.repository;

import liftup.user.model.UserWorkoutDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface UserWorkoutDateRepository extends JpaRepository<UserWorkoutDate, Long> {
    List<UserWorkoutDate> findAllByUsername(String username);
    UserWorkoutDate findByUsernameAndDate(String username, String date);

    @Modifying
    @Transactional
    @Query("delete from UserWorkoutDate uwd where uwd.username = ?1")
    void deleteAllByUsername(String username);
}
