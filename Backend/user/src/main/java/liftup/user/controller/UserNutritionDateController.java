package liftup.user.controller;

import liftup.user.model.UserNutritionDate;
import liftup.user.model.Validation;
import liftup.user.repository.UserNutritionDateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserNutritionDateController {
    @Autowired
    UserNutritionDateRepository userNutritionDateRepo;

    //View all nutrition info for all users
    @GetMapping("/user-nutrition-date")
    List<UserNutritionDate> viewUserNutrition() {
        return userNutritionDateRepo.findAll();
    }

    //View a specific user's nutrition info
    @GetMapping("/user-nutrition-date/{username}")
    List<UserNutritionDate> viewSpecificUsersNutrition(@PathVariable String username) {
        return userNutritionDateRepo.findAllByUsername(username);
    }

    //View an exact username and date
    @GetMapping("/user-nutrition-date/{username}/{date}")
    UserNutritionDate viewExactUserAndDate(@PathVariable String username, @PathVariable String date) {
        return userNutritionDateRepo.findByUsernameAndDate(username, date);
    }

    //Add user nutrition date
    @PostMapping("/user-nutrition-date")
    UserNutritionDate addUserNutritionDate(@RequestBody UserNutritionDate userNutritionDate) {
        return userNutritionDateRepo.save(userNutritionDate);
    }

    //Delete a users nutrition date
    @DeleteMapping("/user-nutrition-date/{username}/{date}")
    Validation deleteUserNutrition(@PathVariable String username, @PathVariable String date) {
        if(userNutritionDateRepo.findByUsernameAndDate(username, date) != null) {
            UserNutritionDate userNutritionDate = userNutritionDateRepo.findByUsernameAndDate(username, date);
            userNutritionDateRepo.delete(userNutritionDate);
            return new Validation(true, "User cleared nutrition for that day.");
        }
        return new Validation(false, "User has not used that day for nutrition yet.");
    }
}
