package com.example.weather_app;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Weather {

    @SerializedName("main")

    private Main main;

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    @SerializedName("weather")
    private List<WeatherJson> weatherJsonList;

    public List<WeatherJson> getWeatherJsonList() {
        return weatherJsonList;
    }

    public void setWeatherJsonList(List<WeatherJson> weatherJsonList) {
        this.weatherJsonList = weatherJsonList;
    }
}
