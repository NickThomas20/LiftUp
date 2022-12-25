package liftup.user.repository;

import liftup.user.model.TrainerApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import javax.transaction.Transactional;

public interface TrainerAppRepository extends JpaRepository<TrainerApp, Long> {
    TrainerApp findByUsername(String username);

    //update sql statement used in controller
    @Modifying
    @Transactional
    @Query("update TrainerApp t set t.trainerSpeciality = ?1 where t.username = ?2")
    int updateSpeciality(String newSpeciality, String username);

    //update sql statement used in controller
    @Modifying
    @Transactional
    @Query("update TrainerApp t set t.YearsActive = ?1 where t.username = ?2")
    int updateYearsActive(int newSpeciality, String username);
}
