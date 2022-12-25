package liftup.user.controller;

import liftup.user.repository.UserWorkoutDateRepository;
import liftup.user.model.UserWorkoutDate;
import liftup.user.model.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class UserWorkoutDateController {
    @Autowired
    UserWorkoutDateRepository userWorkoutDateRepo;

    //View all dates users workout on
    @GetMapping("/user-workout-date")
    List<UserWorkoutDate> viewUsersDates() {
        return userWorkoutDateRepo.findAll();
    }

    //View a specific users workout dates
    @GetMapping("/user-workout-date/{username}")
    List<UserWorkoutDate> viewSpecificUsersDates(@PathVariable String username) {
        return userWorkoutDateRepo.findAllByUsername(username);
    }

    //Add a users workout date
    @PostMapping("/user-workout-date")
    UserWorkoutDate addUserDate(@RequestBody UserWorkoutDate userWorkoutDate) {
        return userWorkoutDateRepo.save(userWorkoutDate);
    }

    //Delete a users workout date
    @DeleteMapping("/user-workout-date/{username}/{date}")
    Validation deleteUserDate(@PathVariable String username, @PathVariable String date) {
        if(userWorkoutDateRepo.findByUsernameAndDate(username, date) != null) {
            UserWorkoutDate userWorkoutDate = userWorkoutDateRepo.findByUsernameAndDate(username, date);
            userWorkoutDateRepo.delete(userWorkoutDate);
            return new Validation(true, "User no longer works out that day.");
        }
        return new Validation(false, "User is not working out that day.");
    }
}
