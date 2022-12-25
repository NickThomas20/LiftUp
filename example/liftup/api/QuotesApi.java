package com.example.liftup.api;

import com.example.liftup.model.Quote;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface QuotesApi {

    @GET("/all-quotes")
    Call<List<Quote>> GetQuotes();

    @POST("/add-quote")
    Call<Quote> addQuote(@Body Quote quote);

    @GET("/all-quotes/random")
    Call<Quote> getRandomQuote();

}
