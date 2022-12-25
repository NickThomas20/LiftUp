package liftup.user.repository;

import liftup.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import javax.transaction.Transactional;
import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {
    boolean existsByEmail(String email);//checks to see if there are any emails in the database
    boolean existsByfname(String fname);//checks to see if there are any first and last names in the database
    boolean existsBylname(String lname);//checks to see if there are any first and last names in the database
    boolean existsByusername(String username);//checks username in database
    boolean existsBypassword(String password);//checks password in database
    User findByUsername(String username);
    List<User> findAllByTrainer(boolean trainer);

    @Modifying
    @Transactional
    @Query("update User u set u.fname = ?1 where u.username = ?2")
    int updateFname(String newFname, String username);
    @Modifying
    @Transactional
    @Query("update User u set u.lname = ?1 where u.username = ?2")
    int updateLname(String newLname, String username);
    @Modifying
    @Transactional
    @Query("update User u set u.email = ?1 where u.username = ?2")
    int updateEmail(String newEmail, String username);
    @Modifying
    @Transactional
    @Query("update User u set u.password = ?1 where u.username = ?2")
    int updatePassword(String newPassword, String username);
    @Modifying
    @Transactional
    @Query("delete from User s where s.username = ?1")
    int deleteUser(String username);
}
