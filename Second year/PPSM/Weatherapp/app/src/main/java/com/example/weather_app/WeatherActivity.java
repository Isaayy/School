package com.example.weather_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.squareup.picasso.Picasso;

import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class WeatherActivity extends AppCompatActivity {

    TextView name, temp , pressure,humidity,tempMin,tempMax ;
    String cityName;
    ImageView weatherIcon ;


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
        weatherIcon = findViewById(R.id.image);

        Bundle extras = getIntent().getExtras();
        if (extras != null){
            cityName = extras.getString("key");
            name.setText(cityName);

            Timer timer = new Timer();
            timer.schedule(new refreshData(), 300000, 300000);

            getData(cityName);

        }

        final SwipeRefreshLayout pullToRefresh = findViewById(R.id.pullToRefresh);
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData(cityName);
                pullToRefresh.setRefreshing(false);
            }
        });
    }



    private void getData(String cityName){

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        Call<Weather> call = apiInterface.getData(cityName);

        call.enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {
                if (!response.isSuccessful()) {
                    finish();
                    return;
                }
                temp.setText(response.body().getMain().getTemp()+" °C");
                pressure.setText(response.body().getMain().getPressure()+" hPa");
                humidity.setText(response.body().getMain().getHumidity()+" %");
                tempMin.setText(response.body().getMain().getTemp_min()+" °C");
                tempMax.setText(response.body().getMain().getTemp_max()+" °C");


                Picasso.get().load("http://openweathermap.org/img/wn/"+response.body().getWeatherJsonList().get(0).getIcon()+"@2x.png").into(weatherIcon);

            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {
            }
        });
    }

    class refreshData extends TimerTask {
        public void run() {
            getData(cityName);
        }
    }



}