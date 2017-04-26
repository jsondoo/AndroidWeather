package com.example.android.android_weather;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class WeatherActivity extends AppCompatActivity {
    private OpenWeatherMap openWeatherMap;
    private TextView textView;
    private TextView textView2;
    private TextView textView3;

    private ActionBar supportActionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        textView = (TextView) findViewById(R.id.text_view);
        textView2 = (TextView) findViewById(R.id.text_view2);
        textView3 = (TextView) findViewById(R.id.text_view3);

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
                textView.setText(openWeatherMap.getTempCelsius() + "C");
                textView2.setText(openWeatherMap.getHumidity() + "%");
                textView3.setText(openWeatherMap.getWeatherDesc());
            }
            else{
                textView.setText("Something went wrong");
            }
        }
    }
}
