package liftup.user.controller;

import liftup.user.repository.NutritionRepository;
import liftup.user.model.Nutrition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class NutritionController {
    @Autowired
    NutritionRepository nutritionRepository;

    //Get all nutrition info
    @GetMapping("/allnutrition")
    List<Nutrition> getAllNutrition() {
        return nutritionRepository.findAll();
    }

    //Get a users nutrition info
    @GetMapping("/{username}/nutrition")
    Nutrition getUsersNutrition(@PathVariable String username) {
        return nutritionRepository.findByUsername(username);
    }

    //Add nutrition info
    @PostMapping("/addnutrition")
    Nutrition addNutrition(@RequestBody Nutrition nutrition) {
        return nutritionRepository.save(nutrition);
    }

    //Edit nutrition info
    @PutMapping("/{username}/editnutrition")
    Nutrition editNutrition(@PathVariable String username, @RequestBody Nutrition nutrition) {
        if(nutritionRepository.findByUsername(username) != null
        && username.equals(nutrition.getUsername())) {
            return nutritionRepository.save(nutrition);
        }
        else {
            return null;
        }
    }

    //Edit calories
    @PutMapping("/{username}/editcalories")
    Nutrition editCalories(@PathVariable String username, @RequestBody int newCalories) {
        nutritionRepository.editCalories(newCalories, username);
        return  nutritionRepository.findByUsername(username);
    }

    //Edit protein
    @PutMapping("/{username}/editprotein")
    Nutrition editProtein(@PathVariable String username, @RequestBody int newProtein) {
        nutritionRepository.editProtein(newProtein, username);
        return  nutritionRepository.findByUsername(username);
    }

    //Edit fat consumed
    @PutMapping("/{username}/editfat")
    Nutrition editFat(@PathVariable String username, @RequestBody int newFat) {
        nutritionRepository.editFat(newFat, username);
        return  nutritionRepository.findByUsername(username);
    }

    //Edit carbs consumed
    @PutMapping("/{username}/editcarbs")
    Nutrition editCarbs(@PathVariable String username, @RequestBody int newCarbs) {
        nutritionRepository.editCarbs(newCarbs, username);
        return  nutritionRepository.findByUsername(username);
    }

    //Delete nutrition info
    @DeleteMapping("/{username}/deletenutrition")
    Nutrition deleteNutrition(@PathVariable String username) {
        if(nutritionRepository.findByUsername(username) != null) {
            Nutrition nutrition = nutritionRepository.findByUsername(username);
            nutritionRepository.delete(nutritionRepository.findByUsername(username));
            return nutrition;
        }
        else {
            return null;
        }
    }

    //Delete calories
    @DeleteMapping("/{username}/deletecalories")
    Nutrition deleteCalories(@PathVariable String username) {
        if(nutritionRepository.findByUsername(username) != null) {
            nutritionRepository.editCalories(0, username);
            return nutritionRepository.findByUsername(username);
        }
        else {
            return null;
        }
    }

    //Delete protein
    @DeleteMapping("/{username}/deleteprotein")
    Nutrition deleteProtein(@PathVariable String username) {
        if(nutritionRepository.findByUsername(username) != null) {
            nutritionRepository.editProtein(0, username);
            return nutritionRepository.findByUsername(username);
        }
        else {
            return null;
        }
    }

    //Delete fat
    @DeleteMapping("/{username}/deletefat")
    Nutrition deleteFat(@PathVariable String username) {
        if(nutritionRepository.findByUsername(username) != null) {
            nutritionRepository.editFat(0, username);
            return nutritionRepository.findByUsername(username);
        }
        else {
            return null;
        }
    }

    //Delete carbs
    @DeleteMapping("/{username}/deletecarbs")
    Nutrition deleteCarbs(@PathVariable String username) {
        if(nutritionRepository.findByUsername(username) != null) {
            nutritionRepository.editCarbs(0, username);
            return nutritionRepository.findByUsername(username);
        }
        else {
            return null;
        }
    }
}
