package com.example.android.android_weather;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class WeatherActivity extends AppCompatActivity {
    private OpenWeatherMap openWeatherMap;


    private TextView weatherInfoTextView;
    private TextView temperatureTextView;
    private TextView humidityTextView;
    private TextView pressureTextView;
    private TextView weatherdescTextView;
    private TextView windspeedTextView;

    private ActionBar supportActionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        temperatureTextView = (TextView) findViewById(R.id.temperature_textview);
        humidityTextView = (TextView) findViewById(R.id.humidity_textview);
        weatherdescTextView = (TextView) findViewById(R.id.weatherdesc_textview);
        weatherInfoTextView = (TextView) findViewById(R.id.weather_info);
        windspeedTextView = (TextView) findViewById(R.id.windspeed_textview);
        pressureTextView = (TextView) findViewById(R.id.pressure_textview);

        supportActionBar = getSupportActionBar();
        supportActionBar.setHomeButtonEnabled(true);
        supportActionBar.setDisplayHomeAsUpEnabled(true); // display home button on action bar

        String latlon = getIntent().getStringExtra("LatLon");
        String[] parts = latlon.split(",");
        new OpenWeatherMapQueryTask().execute(parts[0],parts[1]);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    public class OpenWeatherMapQueryTask extends AsyncTask<String, Void, Boolean> {
        @Override
        protected Boolean doInBackground(String... params) {
            openWeatherMap = new OpenWeatherMap(params[0],params[1]);
            return openWeatherMap.parseJSONdata();
        }

        @Override
        protected void onPostExecute(Boolean success) {
            if(success) {
                supportActionBar.setTitle("Weather for " + openWeatherMap.getCityName());
                temperatureTextView.setText("Temperature: " + openWeatherMap.getTempCelsius() + "°C");
                humidityTextView.setText("Humidity: " + openWeatherMap.getHumidity() + "%");
                weatherdescTextView.setText("Weather: " + openWeatherMap.getWeatherDesc());
                windspeedTextView.setText("Wind Speed: " + openWeatherMap.getWindSpeed() + " m/s");
                pressureTextView.setText("Pressure: " + openWeatherMap.getPressure() + " kPa");
            }
            else{
                weatherInfoTextView.setText("Oops... Something went wrong :(");
            }
        }
    }
}
