package com.example.task3;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface pokeApi {
    String BASE_URL =  "https://pokeapi.co/api/v2/";

    @GET("pokemon")
    Call<poke> getPoke(@Query("limit") int limit, @Query("offset") int offset);

    @GET("item")
    Call<poke> getItem(@Query("limit") int limit, @Query("offset") int offset);

    @GET("location")
    Call<poke> getLocation(@Query("limit") int limit, @Query("offset") int offset);

    @GET("type")
    Call<poke> getType(@Query("limit") int limit, @Query("offset") int offset);

    @GET("region")
    Call<poke> getRegion(@Query("limit") int limit, @Query("offset") int offset);

    @GET("type/{num}")
    Call<Type_Modal_Class> getTypeData(@Path("num") int number);
}
