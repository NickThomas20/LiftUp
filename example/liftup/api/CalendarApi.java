package com.example.liftup.api;
import com.example.liftup.model.*;

import com.example.liftup.Validation;
import com.example.liftup.model.WorkoutDate;

import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * API for the Workout dates.
 */
public interface CalendarApi extends BaseApi {

    //View all dates users workout on
    @GET("/user-workout-date")
    Call<List<WorkoutDate>> viewUsersDates();

    //View a specific users workout dates
    @GET("/user-workout-date/{username}")
    Call<List<WorkoutDate>> viewSpecificUsersDates(@Path("username") String username);

    //Add a users workout date
    @POST("/user-workout-date")
    Call<WorkoutDate> addUserDate(@Body WorkoutDate userWorkoutDate);

    //Delete a users workout date
    @DELETE("/user-workout-date/{username}/{date}")
    Call<Validation> deleteUserDate(@Path("username") String username, @Path("date") String date);
}
