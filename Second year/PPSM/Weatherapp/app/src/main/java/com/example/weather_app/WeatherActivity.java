package com.example.weather_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class WeatherActivity extends AppCompatActivity {

    TextView name, temp , pressure,humidity,tempMin,tempMax ;
    String cityName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        name = findViewById(R.id.name);
        temp = findViewById(R.id.temp);
        pressure = findViewById(R.id.pressure);
        humidity = findViewById(R.id.humidity);
        tempMin = findViewById(R.id.tempMin);
        tempMax = findViewById(R.id.tempMax);


        Bundle extras = getIntent().getExtras();
        if (extras != null){
            cityName = extras.getString("key");
            name.setText(cityName);
            getData(cityName);
        }
    }

    private void getData(String cityName){

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        Call<Weather> call = apiInterface.getData(cityName+",pl");

        call.enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {
                temp.setText(response.body().getMain().getTemp()+" °C");
                pressure.setText(response.body().getMain().getPressure()+" hPa");
                humidity.setText(response.body().getMain().getHumidity()+" %");
                tempMin.setText(response.body().getMain().getTemp_min()+" °C");
                tempMax.setText(response.body().getMain().getTemp_max()+" °C");

            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {
            }
        });
    }
}