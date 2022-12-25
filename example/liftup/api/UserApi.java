package com.example.liftup.api;


import com.example.liftup.Validation;
import com.example.liftup.model.User;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * API for the user data.
 */
public interface UserApi extends BaseApi {

    @GET("/all")
    Call<List<User>> GetAllUsers();

    @POST("/adduser")
    Call<Validation> postUserByPath(@Body User user);

    @GET("/users/{username}")
    Call<User> getSpecificUser(@Path("username") String username);

    @PUT("/{username}/edit/fname")
    Call<Validation> editFname(@Path("userid") String username, @Body String newFname);

    @PUT("/{username}/edit/lname")
    Call<Validation> editLname(@Path("userid") String username, @Body String newLname);

    @PUT("/{username}/{password}/edit/email")
    Call<Validation> editEmail(@Path("username") String username, @Path("password") String password, @Body String newEmail);

    @PUT("/{username}/{password}/edit/password")
    Call<Validation> editPassword(@Path("username")String username, @Path("password") String password, @Body String newPassword);

    @DELETE("/{username}/delete")
    Call<Validation> deleteUser(@Path("username") String username);

    @POST("/login")
    Call<Validation> login(@Body User user);
}

