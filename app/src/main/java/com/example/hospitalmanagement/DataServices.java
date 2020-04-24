package com.example.hospitalmanagement;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface DataServices {


    @POST("/login")
    Call<UserDetails> executeLogin(@Body LoginDetails loginDetails);

    @POST("/user/register")
    Call<UserDetails> executeSignUp(@Body LoginDetails loginDetails);

    @POST("/user/edit")
    Call<String> executeEditUserProfile(@Body UserDetails user);

    @GET("/user/edit/{userID}")
    Call<UserDetails> executeGetUserProfileByID(@Path(value="userID", encoded=true) int userID);

    @POST("/patient/add")
    Call<String> executeAddPatient(@Body Patient patient);


}
