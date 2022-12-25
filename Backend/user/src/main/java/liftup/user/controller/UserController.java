package liftup.user.controller;

import liftup.user.repository.*;
import liftup.user.model.User;
import liftup.user.model.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    NutritionRepository nutritionRepo;
    @Autowired
    StatsRepository statsRepo;
    @Autowired
    TrainerAppRepository trainerAppRepo;
    @Autowired
    UserWorkoutDateRepository userWorkoutDateRepo;
    @Autowired
    UserNutritionDateRepository userNutritionDateRepo;

    //regex for email
    private static final String regexEmail = "^(.+)@(.+)$";
    //allows spaces no non alphabet charcaters
    private static final String regex = "^[\\p{L} .'-]+$";

    @GetMapping("/all")
    List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /*
    This add user function should be for signup only Front-end (elmin / mitch )
    You can take in a new user and it just checks if the first & last name match the correct regex
    The email is also formatted so it has to have the @ symbol along with .(something) at the end
    NICK THOMAS WROTE THIS FUNCTION ASK HIM QUESTIONS IF LOST
     */
    @PostMapping("/adduser")
    Validation postUserByPath(@RequestBody User user) {
        //checks firstname & lastname correctness
        Validation isValid;
        if (user.getFname().matches(regex) && user.getLname().matches(regex) & user.getEmail().matches(regexEmail)) {
            userRepository.save(user);
            isValid = new Validation(true, "Account has successfully been created!");
        } else {
            isValid = new Validation(false,
                    "The firstname: " + user.getFname() + " or Lastname: " + user.getLname() +
                            " or Email: " + user.getEmail() + " doesn't work");
        }
        return isValid;
    }

    //Find a specific user by their username
    @GetMapping("/users/{username}")
    User getSpecificUser(@PathVariable String username) {
        return userRepository.findByUsername(username);
    }

    //Edit functions
    @PutMapping("/{username}/edit/fname")
    Validation editFname(@PathVariable String username, @RequestBody String newFname) {
        Validation isValid;
        if (newFname.matches(regex)) {
            userRepository.updateFname(newFname, username);
            isValid = new Validation(true, "Edited User " + username + "'s first name.");
        } else {
            isValid = new Validation(false, "Not a valid first name.");
        }
        return isValid;
    }

    @PutMapping("/{username}/edit/lname")
    Validation editLname(@PathVariable String username, @RequestBody String newLname) {
        Validation isValid;
        if (newLname.matches(regex)) {
            userRepository.updateLname(newLname, username);
            isValid = new Validation(true, "Edited User " + username + "'s last name.");
        } else {
            isValid = new Validation(false, "Not a valid last name.");
        }
        return isValid;
    }

    @PutMapping("/{username}/{password}/edit/email")
    Validation editEmail(@PathVariable String username, @PathVariable String password, @RequestBody String newEmail) {
        Validation isValid;
        if (password.equals(userRepository.findByUsername(username).getPassword())) {
            if (newEmail.matches(regexEmail)) {
                userRepository.updateEmail(newEmail, username);
                isValid = new Validation(true, "Edited User " + username + "'s email.");
            } else {
                isValid = new Validation(false, "Not a valid email.");
            }
        } else {
            isValid = new Validation(false, "Incorrect password!");
        }
        return isValid;
    }

    @PutMapping("/{username}/{password}/edit/password")
    Validation editPassword(@PathVariable String username, @PathVariable String password, @RequestBody String newPassword) {
        Validation isValid;
        if (password.equals(userRepository.findByUsername(username).getPassword())) {
            userRepository.updatePassword(newPassword, username);
            isValid = new Validation(true, "Edited User " + username + "'s password.");
        } else {
            isValid = new Validation(false, "Incorrect password!");
        }
        return isValid;
    }

    //Delete user function
    @DeleteMapping("/{username}/{password}/delete")
    Validation deleteUser(@PathVariable String username, @PathVariable String password) {
        Validation isValid;
        if (userRepository.findByUsername(username) != null) {
            if (password.equals(userRepository.findByUsername(username).getPassword())) {
                userRepository.deleteUser(username);
                if(nutritionRepo.findByUsername(username) != null) {
                    nutritionRepo.delete(nutritionRepo.findByUsername(username));
                }
                statsRepo.deleteAllByUsername(username);
                if(trainerAppRepo.findByUsername(username) != null) {
                    trainerAppRepo.delete(trainerAppRepo.findByUsername(username));
                }
                userWorkoutDateRepo.deleteAllByUsername(username);
                userNutritionDateRepo.deleteAllByUsername(username);
                isValid = new Validation(true,"Your account has been deleted.");
            } else {
                isValid = new Validation(false, "Incorrect password!");
            }
        } else {
            isValid = new Validation(false,"Not a valid user.");
        }
        return isValid;
    }

    /*
    Front-end ( Mitch / Elmin ) this is a login function that can be used
    to check a user by looking to see if the username and password match what there is in
    the database. Below is a postman example using a JSON format to check and see if the user matches
    this or not.
    {
        "username": "Nick123",
        "fname": "Nick ",
        "lname": "Thomas",
        "email": "Nick@gmail.com",
        "password": "HEYYY"
    }
    return Loggined in as: "Nick123"
    NICK THOMAS WROTE THIS FUNCTION ASK HIM QUESTIONS IF LOST
     */
    @PostMapping("/login")
    Validation login(@RequestBody User existingUser) {
        Validation isValid;
        if (userRepository.existsBypassword(existingUser.getPassword()) && userRepository.existsByusername(existingUser.getUsername())) {
            isValid = new Validation(true, "Logged in as: " + existingUser.getUsername());
        }
        else {
            isValid = new Validation(false, "Sorry your username or password was incorrect");
        }
        return isValid;
    }
}





