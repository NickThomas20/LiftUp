package com.example.liftup.api;

import com.example.liftup.model.Stat;
import com.example.liftup.Validation;
import com.example.liftup.model.Stat;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * API for the statistics data.
 */
public interface StatsApi {

    @GET("/allstats")
    Call<List<Stat>> getCreatedStats();

    @POST("/addastat")
    Call<Stat> addNewStat(@Body Stat newStat);

    @GET("/{username}/onstats")
    Call<List<Stat>> getUsersOnStats(@Path("username") String username);

    @GET("/{username}/{statId}/viewstat")
    List<Stat> viewSpecificStat(@Path("username") String username, @Path("statId") int statId);

    @PUT("/{username}/{statId}/editvalue")
    List<Stat> editStatValue(@Path("username") String username, @Path("statId") int statId, @Body String newValue);

    @PUT("/{username}/{statId}/turnonoff")
    List<Stat> turnOnOff(@Path("username") String username, @Path("statId") int statId, @Body String newValue);

//    @GET("/{username}/stats")
//    Call<List<Stat>> getUsersStats(@Path("username") String username);
}