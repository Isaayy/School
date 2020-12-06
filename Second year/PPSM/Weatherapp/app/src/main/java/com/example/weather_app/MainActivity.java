package com.example.weather_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button btn;
    TextView city ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button) findViewById(R.id.checkWeatherBtn);
        city = (TextView) findViewById(R.id.cityName);

        btn.setOnClickListener(v -> openActivity2(city.getText().toString()));
    }


    public void openActivity2(String city) {
        Intent intent = new Intent(this,WeatherActivity.class);
        intent.putExtra("key",city);
        startActivity(intent);
    }
}