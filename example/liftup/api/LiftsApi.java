package com.example.liftup.api;

import com.example.liftup.model.Lift;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * API for the lift objects.
 */
public interface LiftsApi {

    @GET("/all/lifts")
    Call<List<Lift>> getAllLifts();

    @POST("/addLifts")
    Call<Lift> addLifts(@Body Lift UserLift);

//    @PUT("/updateReps/{exercise}/{newReps}")
//    Call<Lift> updateReps(@Path("exercise") int newReps, String exercise);
//
//    @DELETE("/admin/{adminpassword}/clearLift")
//    Call<Lift> clearLifts(@Path("lift") int lift);
//
//    @DELETE("/admin/{adminpassword}/deleteLift/{liftName}")
//    Call<Lift> deleteLift(@Path("exercise") int newReps, String exercise);


//    @GetMapping("/all/lifts")
//    List<Lifts> getAllLifts(){
//        return liftsRepo.findAll();
//    }
//
//    //FRONT END USE THIS TO ADD A LIFT
//    @PostMapping("/addLifts")
//    Validation addLifts(@RequestBody Lifts Userlifts){
//        liftsRepo.save(Userlifts);
//        return new Validation(true, "Congratulations! You've added a new lift into our database: ");
//    }
//
//
//    //This is a updating feature for reps which should allow the user to change reps of an exercise if they see fit
//    @PutMapping("/updateReps/{exercise}/{newReps}")
//    Validation editReps(@PathVariable String exercise, @PathVariable int newReps) {
//        liftsRepo.updateReps(newReps, exercise);
//        return new Validation(true,
//                "Updated reps to: " + newReps + ", given this exercise: "+ exercise);
//    }
//
//    //This is used specifically for the admin, since we decided to give that person perms
//    //They can wipe the entire database with this function so be careful
//    @DeleteMapping("/admin/{adminpassword}/clearLift")
//    Validation clearAllLifts(@PathVariable String adminpassword) {
//        Validation isValid;
//        if(adminpassword.equals("goodadminpassword")) {
//            while(liftsRepo.findAll().size() > 0) {
//                liftsRepo.clearLifts(liftsRepo.findAll().get(0).getExercise());
//            }
//            isValid = new Validation(true,"Successfully cleared all lifts.");
//        }
//        else {
//            isValid = new Validation(true, "That is not the admin password.");
//        }
//        return isValid;
//    }
//
//    //This gives the admin perms to delete a specific workout
//    @DeleteMapping("/admin/{adminpassword}/deleteLift/{liftName}")
//    Validation deleteLifts(@PathVariable String liftName, @PathVariable String adminpassword){
//        Validation isValid;
//        if(adminpassword.equals("goodadminpassword")) {
//            liftsRepo.deleteLiftsBy(liftName);
//            return new Validation(true, "This lift has been deleted: " + liftName);
//        }else{
//            isValid = new Validation(true, "That is not the admin password.");
//        }
//
//        return isValid;
//}

}