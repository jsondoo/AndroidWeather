package com.example.android.android_weather;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity  extends AppCompatActivity  {

    // preset locations
    private Button UBCButton;
    private Button houstonButton;
    private Button seattleButton;
    private Button newYorkButton;

    private static final String UBCLatLon = "49.246292,-123.116226";
    private static final String houstonLatLon = "29.761993,-95.366302";
    private static final String seattleLatLon = "47.608013,-122.335167";
    private static final String newYorkLatLon = "40.730610,-73.935242";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UBCButton = (Button) findViewById(R.id.UBC_Weather_Button);
        houstonButton = (Button) findViewById(R.id.Houston_Weather_Button);
        seattleButton = (Button) findViewById(R.id.Seattle_Weather_Button);
        newYorkButton = (Button) findViewById(R.id.New_York_Weather_Button);

        UBCButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchWeatherActivity(UBCLatLon);
            }
        });
        houstonButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                launchWeatherActivity(houstonLatLon);
            }
        });
        seattleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchWeatherActivity(seattleLatLon);
            }
        });
        newYorkButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                launchWeatherActivity(newYorkLatLon);
            }
        });

    }

    private void launchWeatherActivity(String latlon){
        Intent intent = new Intent(this, WeatherActivity.class);
        intent.putExtra("LatLon", latlon); // pass latlon through intent
        startActivity(intent);
    }
}
