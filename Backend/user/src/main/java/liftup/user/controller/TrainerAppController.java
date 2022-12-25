package liftup.user.controller;

import liftup.user.repository.TrainerAppRepository;
import liftup.user.model.TrainerApp;
import liftup.user.model.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class TrainerAppController {
    @Autowired
    TrainerAppRepository trainerAppRepository;

    //Testing purposes/ showing everything inside the database
    @GetMapping("/all/TrainerApps")
    List<TrainerApp> getAllTrainerApp(){
        return trainerAppRepository.findAll();
    }

    //FRONT END USE THIS TO ADD A NEW TRAINER APP (MITCH)
    @PostMapping("/addTrainerApp")
    Validation addTrainerApp(@RequestBody TrainerApp trainerapp){
        trainerAppRepository.save(trainerapp);
        return new Validation(true, "Your application has been sucessfully sent ");
    }

    //ability to now update the speciality. Change a speciality based on username
    @PutMapping("/updateSpeciality/{username}/{newSpeciality}")
    Validation editSpeciality(@PathVariable String username, @PathVariable String newSpeciality) {
        //sql function to update speciality, change if needed
        trainerAppRepository.updateSpeciality(newSpeciality, username);
        return new Validation(true,
                "Updated speciality to: " + newSpeciality + ", given this username: "+ username);
    }

    //ability to now update the speciality. Change a speciality based on username
    @PutMapping("/updateSpeciality/{username}/{newYearsActive}")
    Validation editYearsActive(@PathVariable String username, @PathVariable int newYearsActive) {
        //sql function to update years, change if needed
        trainerAppRepository.updateYearsActive(newYearsActive, username);
        return new Validation(true,
                "Updated years active to: " + newYearsActive + ", given this username: "+ username);
    }
}
