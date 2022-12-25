package liftup.user.controller;

import liftup.user.model.*;
import liftup.user.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdminController {
    @Autowired
    UserRepository userRepo;
    @Autowired
    TrainerAppRepository trainerAppRepo;
    @Autowired
    WorkoutRepository workoutRepo;
    @Autowired
    WorkoutInstanceRepository workoutInstanceRepo;
    @Autowired
    LiftsRepository liftsRepo;
    @Autowired
    NutritionRepository nutritionRepo;
    @Autowired
    StatsRepository statsRepo;
    @Autowired
    UserWorkoutDateRepository userWorkoutDateRepo;
    @Autowired
    UserNutritionDateRepository userNutritionDateRepo;

    //View user mappings
    @GetMapping("/admin/all-users")
    List<User> getAllUsers() {
        return userRepo.findAll();
    }
    @GetMapping("/admin/non-trainers")
    List<User> getNonTrainers() {
        return userRepo.findAllByTrainer(false);
    }
    @GetMapping("/admin/trainers")
    List<User> getAllTrainers() {
        return userRepo.findAllByTrainer(true);
    }
    @GetMapping("/admin/users/{username}")
    User getSpecificUser(@PathVariable String username) {
        return userRepo.findByUsername(username);
    }

    //Delete user mapping
    @DeleteMapping("/admin/users/{username}")
    Validation deleteSpecificUser(@PathVariable String username) {
        if(userRepo.findByUsername(username) != null) {
            userRepo.delete(userRepo.findByUsername(username));
            if(nutritionRepo.findByUsername(username) != null) {
                nutritionRepo.delete(nutritionRepo.findByUsername(username));
            }
            statsRepo.deleteAllByUsername(username);
            if(trainerAppRepo.findByUsername(username) != null) {
                trainerAppRepo.delete(trainerAppRepo.findByUsername(username));
            }
            userWorkoutDateRepo.deleteAllByUsername(username);
            userNutritionDateRepo.deleteAllByUsername(username);
            return new Validation(true, "Successfully deleted user.");
        }
        return new Validation(false, "User does not exist.");
    }

    //Swap users admin status mapping
    @PutMapping("/admin/users-admin/{username}")
    User swapAdminStatus(@PathVariable String username) {
        User user = userRepo.findByUsername(username);
        user.swapAdminStatus();
        return userRepo.save(user);
    }

    //Swap users trainer status mapping
    @PutMapping("/admin/users-trainer/{username}")
    User swapTrainerStatus(@PathVariable String username) {
        User user = userRepo.findByUsername(username);
        user.swapTrainerStatus();
        return userRepo.save(user);
    }

    //View trainer application mapping
    @GetMapping("/admin/all-trainer-apps")
    List<TrainerApp> getAllTrainerApps() {
        return trainerAppRepo.findAll();
    }
    @GetMapping("/admin/trainer-apps/{username}")
    TrainerApp getSpecificTrainerApp(@PathVariable String username) {
        return trainerAppRepo.findByUsername(username);
    }

    //Delete trainer application mapping
    @DeleteMapping("/admin/trainer-apps/{username}")
    Validation deleteSpecificTrainerApp(@PathVariable String username) {
        if(trainerAppRepo.findByUsername(username) != null) {
            trainerAppRepo.delete(trainerAppRepo.findByUsername(username));
            return new Validation(true, "Successfully deleted trainer application.");
        }
        return new Validation(false, "That user does not have a trainer application.");
    }

    //Accept a trainer application
    @PutMapping("/admin/trainer-apps/{username}")
    Validation acceptTrainerApp(@PathVariable String username) {
        if(trainerAppRepo.findByUsername(username) != null) {
            User user = userRepo.findByUsername(username);
            user.acceptTrainerApp();
            userRepo.save(user);
            trainerAppRepo.delete(trainerAppRepo.findByUsername(username));
            return new Validation(true, username + " is now a trainer!");
        }
        return new Validation(false, "That user does not have a trainer application.");
    }

    //View workout mappings
    @GetMapping("/admin/all-workouts")
    List<Workout> getAllWorkouts() {
        return workoutRepo.findAll();
    }
    @GetMapping("/admin/workouts/{workoutId}")
    Workout getSpecificWorkout(@PathVariable long workoutId) {
        return workoutRepo.findByWorkoutId(workoutId);
    }

    //View workout instance mappings
    @GetMapping("/admin/all-workout-instances")
    List<WorkoutInstance> getAllWorkoutInstances() {
        return workoutInstanceRepo.findAll();
    }
    @GetMapping("/admin/workout-instance/{workoutId}/{liftId}")
    WorkoutInstance getSpecificWorkoutInstance(@PathVariable long workoutId, @PathVariable long liftId) {
        return workoutInstanceRepo.findByWorkoutIdAndLiftId(workoutId, liftId);
    }
    @GetMapping("/admin/workout-instance-by-workout/{workoutId}")
    List<WorkoutInstance> getByWorkoutId(@PathVariable long workoutId) {
        return workoutInstanceRepo.findAllByWorkoutId(workoutId);
    }
    @GetMapping("/admin/workout-instance-by-lift/{liftId}")
    List<WorkoutInstance> getByLiftId(@PathVariable long liftId) {
        return workoutInstanceRepo.findAllByLiftId(liftId);
    }

    //View lift mapping
    @GetMapping("/admin/all-lifts")
    List<Lifts> getAllLifts() {
        return liftsRepo.findAll();
    }
    @GetMapping("/admin/lifts/{liftId}")
    Lifts getSpecificLift(@PathVariable long liftId) {
        return liftsRepo.findByLiftID(liftId);
    }

    //Delete workout mapping
    @DeleteMapping("/admin/workouts/{workoutId}")
    Validation deleteSpecificWorkout(@PathVariable long workoutId) {
        if(workoutRepo.findByWorkoutId(workoutId) != null) {
            workoutRepo.delete(workoutRepo.findByWorkoutId(workoutId));
            workoutInstanceRepo.deleteAllByWorkoutId(workoutId);
            return new Validation(true, "Successfully deleted workout.");
        }
        return new Validation(false, "That workout does not exist.");
    }

    //Delete lift mapping
    @DeleteMapping("/admin/lifts/{liftId}")
    Validation deleteSpecificLift(@PathVariable long liftId) {
        if(liftsRepo.findByLiftID(liftId) != null) {
            liftsRepo.delete(liftsRepo.findByLiftID(liftId));
            workoutInstanceRepo.deleteAllByLiftId(liftId);
            return new Validation(true, "Successfully deleted lift.");
        }
        return new Validation(false, "That lift does not exist.");
    }
}
