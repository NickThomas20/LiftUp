package com.example.liftup.api;
import com.example.liftup.*;
import com.example.liftup.model.TrainerApplication;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface TrainerApplicationApi extends BaseApi {
    @GET("/all/TrainerApps")
    Call<List<TrainerApplication>> getAllTrainerApp();

    //FRONT END USE THIS TO ADD A NEW TRAINER APP (MITCH)
    @POST("/addTrainerApp")
    Call<Validation> addTrainerApp(@Body() TrainerApplication trainerapp);

    //ability to now update the speciality. Change a speciality based on username
    @PUT("/updateSpeciality/{username}/{newSpeciality}")
    Call<Validation> editSpeciality(@Path("username") String username, @Path("newSpeciality") String newSpeciality);

    //ability to now update the speciality. Change a speciality based on username
    @PUT("/updateSpeciality/{username}/{newYearsActive}")
    Call<Validation> editYearsActive(@Path("username") String username, @Path("newYearsActive") int newYearsActive);

}
