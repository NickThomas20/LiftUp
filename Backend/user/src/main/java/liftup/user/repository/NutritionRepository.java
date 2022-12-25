package liftup.user.repository;

import liftup.user.model.Nutrition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import javax.transaction.Transactional;

public interface NutritionRepository extends JpaRepository<Nutrition, Long> {
    Nutrition findByUsername(String username);

    @Modifying
    @Transactional
    @Query("update Nutrition n set n.caloriesConsumed = ?1 where n.username = ?2")
    void editCalories(int newCalories, String username);
    @Modifying
    @Transactional
    @Query("update Nutrition n set n.proteinConsumed = ?1 where n.username = ?2")
    void editProtein(int newProtein, String username);
    @Modifying
    @Transactional
    @Query("update Nutrition n set n.fatConsumed = ?1 where n.username = ?2")
    void editFat(int newFat, String username);
    @Modifying
    @Transactional
    @Query("update Nutrition n set n.carbsConsumed = ?1 where n.username = ?2")
    void editCarbs(int newCarbs, String username);
}
