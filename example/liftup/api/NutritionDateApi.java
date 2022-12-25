package com.example.liftup.api;

import com.example.liftup.Validation;
import com.example.liftup.model.Nutrition;
import com.example.liftup.model.NutritionDate;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface NutritionDateApi extends BaseApi{
    @GET("/user-nutrition-date")
    Call<List<NutritionDate>> viewUserNutrition();

    //View a specific user's nutrition info
    @GET("/user-nutrition-date/{username}")
    Call<List<NutritionDate>> viewSpecificUsersNutrition(@Path("username") String username);
    //View an exact username and date
    @GET("/user-nutrition-date/{username}/{date}")
    Call<NutritionDate> viewExactUserAndDate(@Path("username") String username, @Path("date") String date);

    //Add user nutrition date
    @POST("/user-nutrition-date")
    Call<NutritionDate> addUserNutritionDate(@Body NutritionDate userNutritionDate);

    //Delete a users nutrition date
    @DELETE("/user-nutrition-date/{username}/{date}")
    Call<Validation> deleteUserNutrition(@Path("username") String username, @Path("date") String date);
}
