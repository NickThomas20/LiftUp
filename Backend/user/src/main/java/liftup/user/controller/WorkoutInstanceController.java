package liftup.user.controller;

import liftup.user.model.Lifts;
import liftup.user.model.Validation;
import liftup.user.model.WorkoutInstance;
import liftup.user.repository.LiftsRepository;
import liftup.user.repository.WorkoutInstanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class WorkoutInstanceController {
    @Autowired
    WorkoutInstanceRepository workoutInstanceRepo;
    @Autowired
    LiftsRepository liftsRepo;

    //View all workout instance
    @GetMapping("/workout-instance")
    List<WorkoutInstance> allWorkoutInstances() {
        return workoutInstanceRepo.findAll();
    }

    //View a specific workouts instances
    @GetMapping("/workout-instance/{id}")
    List<WorkoutInstance> allWorkoutsInstances(@PathVariable long id) {
        return workoutInstanceRepo.findAllByWorkoutId(id);
    }

    //Add a workout instance
    @PostMapping("/workout-instance")
    WorkoutInstance addWorkoutInstance(@RequestBody WorkoutInstance workoutInstance) {
        Lifts lift = liftsRepo.findByLiftID(workoutInstance.getLiftId());
        workoutInstance.setExercise(lift.getExercise());
        return workoutInstanceRepo.save(workoutInstance);
    }

    //Delete a workout instance
    @DeleteMapping("/workout-instance/{workoutId}/{liftId}")
    Validation deleteWorkoutInstance(@PathVariable long workoutId, @PathVariable long liftId) {
        if(workoutInstanceRepo.findByWorkoutIdAndLiftId(workoutId, liftId) != null) {
            WorkoutInstance workoutInstance = workoutInstanceRepo.findByWorkoutIdAndLiftId(workoutId, liftId);
            workoutInstanceRepo.delete(workoutInstance);
            return new Validation(true, "Deleted that workout instance.");
        }
        return new Validation(false, "That workout does not use that lift.");
    }
}
