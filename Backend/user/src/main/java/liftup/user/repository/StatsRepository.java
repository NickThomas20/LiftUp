package liftup.user.repository;

import liftup.user.model.Stats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import javax.transaction.Transactional;
import java.util.List;

public interface StatsRepository extends JpaRepository<Stats, Long> {
    Stats findByStatId(int statId);
    List<Stats> findAllByIsOn(boolean isOn);
    List<Stats> findAllByUsernameAndIsOn(String username, boolean b);

    @Modifying
    @Transactional
    @Query("delete from Stats s where s.username = ?1")
    void deleteAllByUsername(String username);

    //Edit Stats
    @Modifying
    @Transactional
    @Query("update Stats s set s.value = ?1 where s.statId = ?2 and s.username = ?3")
    void editValue(String newValue, int statId, String username);
    @Modifying
    @Transactional
    @Query("update Stats s set s.isOn = ?1 where s.statId = ?2 and s.username = ?3")
    void turnOnOff(boolean newStatus, int statId, String username);
    @Modifying
    @Transactional
    @Query("delete from Stats s where s.statId = ?1 and s.username = ?2")
    void deleteStat(int statId, String username);
}
