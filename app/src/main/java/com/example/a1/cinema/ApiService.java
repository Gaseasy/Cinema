package com.example.a1.cinema;


import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService
{
    @FormUrlEncoded
    @POST("register.php")
    Call<Result> createUser(
            @Field("name") String name,
            @Field("email") String email,
            @Field("password") String password);

    @FormUrlEncoded
    @POST("login.php")
    Call<Result> loginUser(
            @Field("email") String email,
            @Field("password") String password);

}
