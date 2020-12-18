package com.example.weather_app;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherJson {
    @SerializedName("icon")

    String icon;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
