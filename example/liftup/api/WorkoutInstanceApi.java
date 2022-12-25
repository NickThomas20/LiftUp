package com.example.liftup.api;

import com.example.liftup.Validation;
import com.example.liftup.model.WorkoutInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface WorkoutInstanceApi extends BaseApi {
    //View all workout instance
    @GET("/workout-instance")
    Call<List<WorkoutInstance>> allWorkoutInstances();

    //View a specific workouts instances
    @GET("/workout-instance/{id}")
    Call<List<WorkoutInstance>> allWorkoutsInstances(@Path("id") long id);

    //Add a workout instance
    @POST("/workout-instance")
    Call<WorkoutInstance> addWorkoutInstance(@Body WorkoutInstance workoutInstance);

    //Delete a workout instance
    @DELETE("/workout-instance")
    Call<Validation> deleteWorkoutInstance(@Body WorkoutInstance workoutInstance);

}