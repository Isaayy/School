package com.example.weather_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btn;
    TextView city ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button) findViewById(R.id.checkWeatherBtn);
        city = (TextView) findViewById(R.id.cityName);

        if(isNetwork()) {
            btn.setOnClickListener(v -> openActivity2(city.getText().toString()));
        }
        else{
            Toast.makeText(MainActivity.this,"Internet connection required",Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isNetwork(){
        boolean wifi = false;
        boolean mobileData = false;

        ConnectivityManager connectivityManager=(ConnectivityManager)getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo[] networkInfos = connectivityManager.getAllNetworkInfo();

        for (NetworkInfo info:networkInfos){
            if(info.getTypeName().equalsIgnoreCase("WIFI"))
                if(info.isConnected())
                    wifi=true;
            if(info.getTypeName().equalsIgnoreCase("MOBILE"))
                if(info.isConnected())
                    mobileData=true;
        };
        return wifi || mobileData;
    }


    public void openActivity2(String city) {
        Intent intent = new Intent(this,WeatherActivity.class);
        intent.putExtra("key",city);
        startActivity(intent);
    }
}