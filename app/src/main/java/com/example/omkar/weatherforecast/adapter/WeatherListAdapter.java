package com.example.omkar.weatherforecast.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.omkar.weatherforecast.entity.WeatherForecast;
import com.example.omkar.weatherforecast.view.WeatherForecastListView;

import java.util.ArrayList;

/**
 * Created by Omkar on 13-Aug-15.
 */
public class WeatherListAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<WeatherForecast> mWeatherForecasts;
    private WeatherForecastListView mWeatherForecastListView;

    public WeatherListAdapter(Context context, ArrayList<WeatherForecast> weatherForecasts) {
        this.mContext = context;
        this.mWeatherForecasts = weatherForecasts;
    }

    @Override
    public int getCount() {
        if (mWeatherForecasts!=null){
            return mWeatherForecasts.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView==null){
            mWeatherForecastListView=new WeatherForecastListView(mContext,this);
        }
        else {
            mWeatherForecastListView= (WeatherForecastListView) convertView;
        }

        WeatherForecast weatherForecast=mWeatherForecasts.get(position);
        mWeatherForecastListView.setWeatherView(mWeatherForecasts,weatherForecast);

        return mWeatherForecastListView;
    }

    public void notifyChange(ArrayList<WeatherForecast> weatherForecasts){
        mWeatherForecasts=weatherForecasts;
        notifyDataSetChanged();
    }
}
