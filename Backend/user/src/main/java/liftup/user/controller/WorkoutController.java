package liftup.user.controller;

import liftup.user.repository.LiftsRepository;
import liftup.user.repository.WorkoutRepository;
import liftup.user.model.Validation;
import liftup.user.model.Workout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class WorkoutController {
    @Autowired
    WorkoutRepository workoutRepo;

    @Autowired
    LiftsRepository liftsRepo;

    @GetMapping("/all/workouts")
    List<Workout> getAllWorkouts(){
        return workoutRepo.findAll();
    }

    //FRONT END USE THIS TO ADD A WORKOUT
    //Nick side note figure out why id doesn't start from 0
    @PostMapping("/addWorkout")
    Validation addWorkout(@RequestBody Workout workout){

            workoutRepo.save(workout);
            return new Validation(true, "This workout has been added successfully!");

    }

    //Ability to update the author now
    @PutMapping("/updateAuthor/{workoutId}/{newAuthor}")
    Validation editAuhtor(@PathVariable String newAuthor, @PathVariable int workoutId) {
        workoutRepo.updateAuthor(newAuthor, workoutId);
        return new Validation(true,
                "Updated author to: " + newAuthor + ", given this id: "+ workoutId);
    }

    //Ability to update the date now
    @PutMapping("/updateDate/{workoutId}/{newDate}")
    Validation editDate(@PathVariable String newDate, @PathVariable int workoutId) {
        workoutRepo.updateDate(newDate, workoutId);
        return new Validation(true,
                "Updated date to: " + newDate + ", given this id: "+ workoutId);
    }
}
