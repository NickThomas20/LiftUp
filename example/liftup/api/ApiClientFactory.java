package com.example.liftup.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Helper class to retrieve api's.
 */
public class ApiClientFactory {

    static Retrofit apiClientSeed = null;

    static Retrofit GetApiClientSeed() {
        if (apiClientSeed == null) {

            Gson gson = new GsonBuilder()
                    //.setLenient()
                    .create();

            //https://jsonplaceholder.typicode.com/posts/1
            //https://jsonplaceholder.typicode.com/
            apiClientSeed = new Retrofit.Builder()
                    .baseUrl("http://coms-309-014.class.las.iastate.edu:8080/")
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return apiClientSeed;
    }

    public static UserApi GetUserApi(){ return GetApiClientSeed().create(UserApi.class);}

    public static StatsApi GetStatsApi(){ return GetApiClientSeed().create(StatsApi.class);}

    public static CalendarApi GetCalendarApi(){ return GetApiClientSeed().create(CalendarApi.class);}

    public static NutritionApi GetNutritionApi(){return GetApiClientSeed().create(NutritionApi.class);}

    public static LiftsApi GetLiftsApi(){ return GetApiClientSeed().create(LiftsApi.class);}

    public static WorkoutApi GetWorkoutApi() { return  GetApiClientSeed().create(WorkoutApi.class);}

    public static QuotesApi GetQuoteApi() { return GetApiClientSeed().create(QuotesApi.class);}

    public static TrainerApplicationApi GetTrainerApplicationApi() { return GetApiClientSeed().create(TrainerApplicationApi.class);}

    public static AdminApi GetAdminApi() { return GetApiClientSeed().create(AdminApi.class);}

    public static NutritionDateApi GetNutritionDateApi() { return GetApiClientSeed().create(NutritionDateApi.class);}

    public static WorkoutInstanceApi GetWorkoutInstanceApi() { return GetApiClientSeed().create(WorkoutInstanceApi.class);}


}
