package com.example.omkar.weatherforecast;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.omkar.weatherforecast.entity.WeatherForecast;
import com.squareup.picasso.Picasso;

public class WeatherDetail extends AppCompatActivity {

    private TextView mTxtCityName, mTxtLagLat, mTxtTemp, mTxtPressure;    //295.34C 952.2hpa
    private TextView mTxtHumidity, mTxtCloudiness;         //moderate rain
    private TextView mTxtSpeed, mTxtRain;         //2.13 m/s   8.23
    private TextView mTxtClouds, mTxtDate;
    private ImageView mImageIcon;

    private WeatherForecast mWeatherForecast;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecase_detail);

        Intent intent = getIntent();

        mWeatherForecast = (WeatherForecast) intent.getSerializableExtra("DATA");
        mImageIcon = (ImageView) findViewById(R.id.imageIcon);
        mTxtCityName = (TextView) findViewById(R.id.txtCityName);
        mTxtTemp = (TextView) findViewById(R.id.txtTemp);
        mTxtDate = (TextView) findViewById(R.id.txtDate);
        mTxtCloudiness = (TextView) findViewById(R.id.txtCloudiness);
        mTxtLagLat = (TextView) findViewById(R.id.txtLogLat);
        mTxtPressure = (TextView) findViewById(R.id.txtPressure);
        mTxtHumidity = (TextView) findViewById(R.id.txtHumidity);
        mTxtSpeed = (TextView) findViewById(R.id.txtSpeed);
        mTxtRain = (TextView) findViewById(R.id.txtRain);
        mTxtClouds = (TextView) findViewById(R.id.txtClouds);

        String url = "http://openweathermap.org/img/w/" + mWeatherForecast.getImageIcon() + ".png";
        Picasso.with(this).load(url).into(mImageIcon);
        mTxtCityName.setText("City Name : " + mWeatherForecast.getCityName());
        mTxtTemp.setText("Temperature : " + mWeatherForecast.getTemp() + "ºC");
        mTxtDate.setText("Date : " + mWeatherForecast.getDate());
        mTxtCloudiness.setText("Cloudiness : " + mWeatherForecast.getCloudiness());
        mTxtLagLat.setText("Lon : " + mWeatherForecast.getLag() + " Lat : " + mWeatherForecast.getLat());
        mTxtPressure.setText("Pressure : " + mWeatherForecast.getPressure() + " hpa");
        mTxtHumidity.setText("Humidity : " + mWeatherForecast.getHumidity() + " %");
        mTxtSpeed.setText("Speed : " + mWeatherForecast.getSpeed() + " m/s");
        mTxtRain.setText("Rain : " + mWeatherForecast.getRain());
        mTxtClouds.setText("Clouds : " + mWeatherForecast.getClouds() + " %");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_weather_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
