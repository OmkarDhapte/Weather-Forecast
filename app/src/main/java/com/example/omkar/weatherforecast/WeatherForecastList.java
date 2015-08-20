package com.example.omkar.weatherforecast;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.omkar.weatherforecast.adapter.WeatherListAdapter;
import com.example.omkar.weatherforecast.entity.WeatherForecast;
import com.example.omkar.weatherforecast.thread.WeatherforecastLoader;

import java.util.ArrayList;

public class WeatherForecastList extends AppCompatActivity {

    private ListView mWeatherList;
    private WeatherListAdapter mWeatherListAdapter;
    private ArrayList<WeatherForecast> mWeatherForecasts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_forecast);
        final Intent intent=getIntent();
        String cityName=intent.getStringExtra("CITY_NAME");

        try {

            ActionBar actionBar = getSupportActionBar();
            actionBar.setTitle(cityName.toUpperCase());
        }catch (Exception e){}

        mWeatherForecasts=new ArrayList<WeatherForecast>();

        mWeatherList= (ListView) findViewById(R.id.listView);
        mWeatherListAdapter=new WeatherListAdapter(this,mWeatherForecasts);
        mWeatherList.setAdapter(mWeatherListAdapter);

        new WeatherforecastLoader(this,new MyHandler()).execute(cityName);

        mWeatherList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                WeatherForecast temp1 = mWeatherForecasts.get(position);
                try {

                    Intent inten = new Intent(WeatherForecastList.this,WeatherDetail.class);
                    inten.putExtra("DATA", temp1);
                    startActivity(inten);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

    }

    class MyHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            if (msg.obj != null) {
                mWeatherForecasts = (ArrayList<WeatherForecast>) msg.obj;
                mWeatherListAdapter.notifyChange(mWeatherForecasts);

            } else {
                Toast.makeText(WeatherForecastList.this, "Unable to fetch data try again !", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_weather_forecast, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.searchAction).getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                try {
                    ActionBar actionBar = getSupportActionBar();
                    actionBar.setTitle(query.toUpperCase());
                }catch (Exception e){}
                new WeatherforecastLoader(WeatherForecastList.this,new MyHandler()).execute(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
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
        if (id==R.id.searchAction){

        }

        return super.onOptionsItemSelected(item);
    }
}
