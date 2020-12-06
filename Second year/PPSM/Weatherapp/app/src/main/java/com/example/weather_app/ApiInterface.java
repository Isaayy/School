package com.example.weather_app;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("weather?APPID=749561a315b14523a8f5f1ef95e45864&units=metric")
    Call<Weather> getData(@Query("q") String name);
}
