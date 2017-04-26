package com.example.android.android_weather;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity  extends AppCompatActivity  {

    // preset locations
    private Button UBCButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UBCButton = (Button) findViewById(R.id.UBC_Weather_Button);
        UBCButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchWeatherActivity();
            }
        });

    }

    private void launchWeatherActivity(){
        Intent intent = new Intent(this, WeatherActivity.class);
        intent.putExtra("LatLon", "49.2765,-123.2177"); // pass latlon through intent
        startActivity(intent);
    }







}
