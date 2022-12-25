package com.example.liftup.api;
import com.example.liftup.model.*;
import com.example.liftup.*;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface AdminApi extends BaseApi {
    @GET("/admin/all-users")
    Call<List<User>> getAllUsers();

    @GET("/admin/non-trainers")
    Call<List<User>> getNonTrainers();

    @GET("/admin/trainers")
    Call<List<User>> getAllTrainers();

    @GET("/admin/users/{username}")
    Call<User> getSpecificUser(@Path("username") String username);

    //Delete user mapping
    @DELETE("/admin/users/{username}")
    Call<Validation> deleteSpecificUser(@Path("username") String username);

    //Swap users admin status mapping
    @PUT("/admin/users-admin/{username}")
    Call<User> swapAdminStatus(@Path("username") String username);

    //Swap users trainer status mapping
    @PUT("/admin/users-trainer/{username}")
    Call<User> swapTrainerStatus(@Path("username") String username);

    //View trainer application mapping
    @GET("/admin/all-trainer-apps")
    Call<List<TrainerApplication>> getAllTrainerApps();

    @GET("/admin/trainer-apps/{username}")
    Call<TrainerApplication> getSpecificTrainerApp(@Path("username") String username);

    //Delete trainer application mapping
    @DELETE("/admin/trainer-apps/{username}")
    Call<Validation> deleteSpecificTrainerApp(@Path("username") String username);

    //Accept a trainer application
    @PUT("/admin/trainer-apps/{username}")
    Call<Validation> acceptTrainerApp(@Path("username") String username) ;

    //View workout mappings
    @GET("/admin/all-workouts")
    Call<List<Workout>> getAllWorkouts();

    @GET("/admin/workouts/{workoutId}")
    Call<Workout> getSpecificWorkout(@Path("workoutId") long workoutId);

    //View workout instance mappings
    @GET("/admin/all-workout-instances")
    Call<List<Workout>> getAllWorkoutInstances();

    @GET("/admin/workout-instance/{workoutId}/{liftId}")
    Call<Workout> getSpecificWorkoutInstance(@Path("workoutId") long workoutId, @Path("liftId") long liftId);

    @GET("/admin/workout-instance-by-workout/{workoutId}")
    Call<List<Workout>> getByWorkoutId(@Path("workoutId") long workoutId);

    @GET("/admin/workout-instance-by-lift/{liftId}")
    Call<List<Workout>> getByLiftId(@Path("liftId") long liftId);

//    //View lift mapping
//    @GET("/admin/all-lifts")
//    Call<List<Lifts>> getAllLifts();
//
//    @GET("/admin/lifts/{liftId}")
//    Call<Lifts> getSpecificLift(@Path("liftId") long liftId);

    //Delete workout mapping
    @DELETE("/admin/workouts/{workoutId}")
    Call<Validation> deleteSpecificWorkout(@Path("workoutId") long workoutId);

    //Delete lift mapping
    @DELETE("/admin/lifts/{liftId}")
    Call<Validation> deleteSpecificLift(@Path("liftId") long liftId);
}
