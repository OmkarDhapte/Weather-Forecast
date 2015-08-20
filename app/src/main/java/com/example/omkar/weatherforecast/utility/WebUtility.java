package com.example.omkar.weatherforecast.utility;

import com.example.omkar.weatherforecast.entity.WeatherForecast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Omkar on 13-Aug-15.
 */
public class WebUtility {

    static private ArrayList<WeatherForecast> mWeatherForecasts;
    static private WeatherForecast mWeatherForecast;

    public static ArrayList<WeatherForecast> getContent(String cityName) {

        try {
            URL url = new URL("http://api.openweathermap.org/data/2.5/forecast/daily?q="+cityName+"&mode=json&unit=metric&cnt=14");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            InputStream in = new BufferedInputStream(httpURLConnection.getInputStream());

            byte arr[] = new byte[1024];
            int count;
            StringBuilder sb = new StringBuilder();
            while ((count = in.read(arr)) != -1) {
                sb.append(new String(arr, 0, count));

            }
            in.close();
            mWeatherForecasts = new ArrayList<WeatherForecast>();
            mWeatherForecast = new WeatherForecast();

            double log,lat;

            DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yy");
            Date date = new Date();
            String mod_date=dateFormat.format(date);

            JSONObject jsonObject = new JSONObject(sb.toString());
            JSONObject jsonObject1 = jsonObject.getJSONObject("city");
            cityName=jsonObject1.getString("name");
            JSONObject jsonObject2 = jsonObject1.getJSONObject("coord");
            log=jsonObject2.getDouble("lon");
            lat=jsonObject2.getDouble("lat");

            JSONArray jsonArray = jsonObject.getJSONArray("list");

            for (int i = 0; i < jsonArray.length(); i++) {

                mWeatherForecast = new WeatherForecast();

                mWeatherForecast.setDate(mod_date);
                Calendar c = Calendar.getInstance();
                c.setTime(dateFormat.parse(mod_date));
                c.add(Calendar.DATE, 1);  // number of days to add
                mod_date = dateFormat.format(c.getTime());

                mWeatherForecast.setCityName(cityName);
                mWeatherForecast.setLag(log);
                mWeatherForecast.setLat(lat);
                JSONObject jsonObject3 = jsonArray.getJSONObject(i);
                JSONObject jsonObject4 = jsonObject3.getJSONObject("temp");
                double far=jsonObject4.getDouble("day");
                double celcius=far-273.0;
                mWeatherForecast.setTemp(Double.parseDouble(String.format("%.2f",celcius)));
                mWeatherForecast.setPressure(jsonObject3.getDouble("pressure"));
                mWeatherForecast.setHumidity(jsonObject3.getInt("humidity"));
                JSONArray jsonArray1 = jsonObject3.getJSONArray("weather");
                JSONObject jsonObject5 = jsonArray1.getJSONObject(0);
                mWeatherForecast.setCloudiness(jsonObject5.getString("description"));
                mWeatherForecast.setImageID(jsonObject5.getInt("id"));
                mWeatherForecast.setImageIcon(jsonObject5.getString("icon"));
                mWeatherForecast.setSpeed(jsonObject3.getDouble("speed"));
                mWeatherForecast.setDeg(jsonObject3.getDouble("deg"));
                mWeatherForecast.setClouds(jsonObject3.getDouble("clouds"));
                try {
                    mWeatherForecast.setRain(jsonObject3.getDouble("rain"));
                }
                catch (JSONException e){
                    mWeatherForecast.setRain(0);
                }
                mWeatherForecasts.add(mWeatherForecast);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return mWeatherForecasts;
    }
}
