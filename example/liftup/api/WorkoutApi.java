package com.example.liftup.api;

import com.example.liftup.Validation;
import com.example.liftup.model.Workout;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * API for the workout data.
 */
public interface WorkoutApi extends BaseApi {

    @GET("/all/workouts")
    Call<List<Workout>> getAllWorkouts();

    //FRONT END USE THIS TO ADD A WORKOUT
    //Nick side note figure out why id doesn't start from 0
    @POST("/addWorkout")
    Call<Validation> addWorkout(@Body Workout workout);

    //Ability to update the author now
    @PUT("/updateAuthor/{workoutId}/{newAuthor}")
    Call<Validation> editAuthor(@Path("newAuthor") String newAuthor, @Path("workoutId") int workoutId);

    //Ability to update the date now
    @PUT("/updateDate/{workoutId}/{newDate}")
    Call<Validation> editDate(@Path("newDate") String newDate, @Path("workoutId") int workoutId);

//    //ability to delete a workout plan here
//    @DELETE("/admin/{adminpassword}/deleteWorkout/{author}")
//    Call<Validation> deleteWorkout(@Path("author") String author, @Path("adminpassword") String adminpassword);


//See all lifts based on workout id ( FRONTEND CALL THIS TO SEE WHAT LIFTS ARE ASSOCIATED TO WHAT PERSON)
//@PUT("/workout/returnall/{workoutID}")
//    List<Lifts> workoutLifts(@Path("workoutID") long workoutID);

//    //This is what links the two tables together
//    @PUT("/workout/{workoutID}/lifts/{liftID}")
//    Validation addedLiftToWorkout(@Path("liftID") long liftID, @Path("workoutID") long workoutID);

}
