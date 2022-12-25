package liftup.user.controller;

import liftup.user.model.WorkoutInstance;
import liftup.user.repository.LiftsRepository;
import liftup.user.model.Lifts;
import liftup.user.model.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class LiftsController {
    @Autowired
    LiftsRepository liftsRepo;

    @GetMapping("/all/lifts")
    List<Lifts> getAllLifts(){
        return liftsRepo.findAll();
    }
    //View a specific workouts instances
    @GetMapping("/lifts/{id}")
    Lifts allWorkoutsInstances(@PathVariable long id) {
        return liftsRepo.findByLiftID(id);
    }

    //FRONT END USE THIS TO ADD A LIFT
    @PostMapping("/addLifts")
    Validation addLifts(@RequestBody Lifts Userlifts){
        liftsRepo.save(Userlifts);
        return new Validation(true, "Congratulations! You've added a new lift into our database: ");
    }
}
