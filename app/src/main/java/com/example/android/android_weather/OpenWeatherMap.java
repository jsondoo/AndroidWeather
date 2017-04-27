package com.example.android.android_weather;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

// Class for handling API requests, and parsing JSON data
public class OpenWeatherMap {
    private static final String API_KEY = Config.API_KEY;
    private static final String BASE_URL = "http://api.openweathermap.org/data/2.5/weather?";

    private String lat;
    private String lon;

    // location-related data
    private String tempCelsius = null;
    private String weatherDesc = null;
    private String humidity = null;  // %
    private String pressure = null;  // kPa
    private String windSpeed = null; // meters / second
    private String cityName = null;

    public OpenWeatherMap(){}

    public OpenWeatherMap(String lat, String lon){
        this.lat = lat;
        this.lon = lon;
    }

    public void setLatLon(String lat, String lon){
        this.lat = lat;
        this.lon = lon;
    }

    // returns true if parsing was successful
    public boolean parseJSONdata() {
        String data;

        try {
            data = getJSONdata();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        try {
            // Log.d("JSON response", data);
            JSONObject weatherData = new JSONObject(data);
            JSONObject main = weatherData.getJSONObject("main");
            tempCelsius = String.valueOf((int) (main.getDouble("temp") - 273.15)); // kelvin -> celsius
            pressure = String.valueOf(main.getInt("pressure") / 10); // hPa -> kPa
            humidity = main.getString("humidity");

            JSONObject weather = weatherData.getJSONArray("weather").getJSONObject(0);
            weatherDesc = weather.getString("description");

            JSONObject wind = weatherData.getJSONObject("wind");
            windSpeed = wind.getString("speed");

            cityName = weatherData.getString("name");

        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }


    // helper for sending HTTP GET request using lat and lon
    private String getJSONdata() throws IOException {
        String url = BASE_URL + "lat=" + lat + "&lon=" + lon +"&appid=" + API_KEY;

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }

    public String getTempCelsius(){
        return this.tempCelsius;
    }

    public String getLat() {
        return lat;
    }

    public String getLon() {
        return lon;
    }

    public String getWeatherDesc() {
        return weatherDesc;
    }

    public String getHumidity() {
        return humidity;
    }

    public String getPressure() {
        return pressure;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public String getCityName(){
        return cityName;
    }

}
