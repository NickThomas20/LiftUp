package liftup.user.repository;

import liftup.user.model.UserNutritionDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface UserNutritionDateRepository extends JpaRepository<UserNutritionDate, Long> {
    List<UserNutritionDate> findAllByUsername(String username);
    UserNutritionDate findByUsernameAndDate(String username, String date);

    @Modifying
    @Transactional
    @Query("delete from UserNutritionDate und where und.username = ?1")
    void deleteAllByUsername(String username);
}
