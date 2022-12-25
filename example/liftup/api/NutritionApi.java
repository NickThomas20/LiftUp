package com.example.liftup.api;


import com.example.liftup.model.Nutrition;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * API for the nutrition datas.
 */
public interface NutritionApi {

    @GET("/allnutrition")
    Call<Nutrition> getAllNutrition();

    @GET("/{username}/nutrition")
    Call<Nutrition> getUsersNutrition(@Path("username") String username);

    @POST("/addnutrition")
    Call<Nutrition> addNutrition(@Body Nutrition newNutrition);

    @PUT("/{username}/editnutrition")
    Call<Nutrition> editNutrition(@Path("username") String username);

    @PUT("/{username}/editcalories")
    Call<Nutrition> editCalories(@Path("username") String username);

    @PUT("/{username}/editprotein")
    Call<Nutrition> editProtein(@Path("username") String username);

    @PUT("/{username}/editfat")
    Call<Nutrition> editFat(@Path("username") String username);

    @PUT("/{username}/editcarbs")
    Call<Nutrition> editCarbs(@Path("username") String username);

    @DELETE("/{username}/deletenutrition")
    Call<Nutrition> deleteNutrition(@Path("username") String username);

    @DELETE("/{username}/deletecalories")
    Call<Nutrition> deleteCalories(@Path("username") String username);

    @DELETE("/{username}/deleteprotein")
    Call<Nutrition> deleteProtein(@Path("username") String username);

    @DELETE("/{username}/deletefat")
    Call<Nutrition> deleteFat(@Path("username") String username);

    @DELETE("/{username}/deletecarbs")
    Call<Nutrition> deleteCarbs(@Path("username") String username);

}

//    //Get all nutrition info
//    @GetMapping("/allnutrition")
//    List<Nutrition> getAllNutrition() {
//        return nutritionRepository.findAll();
//    }
//
//    //Get a users nutrition info
//    @GetMapping("/{username}/nutrition")
//    Nutrition getUsersNutrition(@PathVariable String username) {
//        return nutritionRepository.findByUsername(username);
//    }
//
//    //Add nutrition info
//    @PostMapping("/addnutrition")
//    Nutrition addNutrition(@RequestBody Nutrition nutrition) {
//        return nutritionRepository.save(nutrition);
//    }
//
//    //Edit nutrition info
//    @PutMapping("/{username}/editnutrition")
//    Nutrition editNutrition(@PathVariable String username, @RequestBody Nutrition nutrition) {
//        if(nutritionRepository.findByUsername(username) != null
//                && username.equals(nutrition.getUsername())) {
//            return nutritionRepository.save(nutrition);
//        }
//        else {
//            return null;
//        }
//    }
//
//    //Edit calories
//    @PutMapping("/{username}/editcalories")
//    Nutrition editCalories(@PathVariable String username, @RequestBody int newCalories) {
//        nutritionRepository.editCalories(newCalories, username);
//        return  nutritionRepository.findByUsername(username);
//    }
//
//    //Edit protein
//    @PutMapping("/{username}/editprotein")
//    Nutrition editProtein(@PathVariable String username, @RequestBody int newProtein) {
//        nutritionRepository.editProtein(newProtein, username);
//        return  nutritionRepository.findByUsername(username);
//    }
//
//    //Edit fat consumed
//    @PutMapping("/{username}/editfat")
//    Nutrition editFat(@PathVariable String username, @RequestBody int newFat) {
//        nutritionRepository.editFat(newFat, username);
//        return  nutritionRepository.findByUsername(username);
//    }
//
//    //Edit carbs consumed
//    @PutMapping("/{username}/editcarbs")
//    Nutrition editCarbs(@PathVariable String username, @RequestBody int newCarbs) {
//        nutritionRepository.editCarbs(newCarbs, username);
//        return  nutritionRepository.findByUsername(username);
//    }
//
//    //Delete nutrition info
//    @DeleteMapping("/{username}/deletenutrition")
//    Nutrition deleteNutrition(@PathVariable String username) {
//        if(nutritionRepository.findByUsername(username) != null) {
//            Nutrition nutrition = nutritionRepository.findByUsername(username);
//            nutritionRepository.delete(nutritionRepository.findByUsername(username));
//            return nutrition;
//        }
//        else {
//            return null;
//        }
//    }
//
//    //Delete calories
//    @DeleteMapping("/{username}/deletecalories")
//    Nutrition deleteCalories(@PathVariable String username) {
//        if(nutritionRepository.findByUsername(username) != null) {
//            nutritionRepository.editCalories(0, username);
//            return nutritionRepository.findByUsername(username);
//        }
//        else {
//            return null;
//        }
//    }
//
//    //Delete protein
//    @DeleteMapping("/{username}/deleteprotein")
//    Nutrition deleteProtein(@PathVariable String username) {
//        if(nutritionRepository.findByUsername(username) != null) {
//            nutritionRepository.editProtein(0, username);
//            return nutritionRepository.findByUsername(username);
//        }
//        else {
//            return null;
//        }
//    }
//
//    //Delete fat
//    @DeleteMapping("/{username}/deletefat")
//    Nutrition deleteFat(@PathVariable String username) {
//        if(nutritionRepository.findByUsername(username) != null) {
//            nutritionRepository.editFat(0, username);
//            return nutritionRepository.findByUsername(username);
//        }
//        else {
//            return null;
//        }
//    }
//
//    //Delete carbs
//    @DeleteMapping("/{username}/deletecarbs")
//    Nutrition deleteCarbs(@PathVariable String username) {
//        if(nutritionRepository.findByUsername(username) != null) {
//            nutritionRepository.editCarbs(0, username);
//            return nutritionRepository.findByUsername(username);
//        }
//        else {
//            return null;
//        }
//    }
//
