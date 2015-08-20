package com.example.omkar.weatherforecast.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.omkar.weatherforecast.R;
import com.example.omkar.weatherforecast.adapter.WeatherListAdapter;
import com.example.omkar.weatherforecast.entity.WeatherForecast;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Omkar on 13-Aug-15.
 */
public class WeatherForecastListView extends LinearLayout {

    private ImageView mImageView;
    private TextView mTxtDate,mTxtDegC,mTxtCloudiness;
    private WeatherForecast mWeatherForecast;
    private ArrayList<WeatherForecast> mWeatherForecasts;
    private WeatherListAdapter mWeatherListAdapter;
    private Context mContext;

    public WeatherForecastListView(Context context,WeatherListAdapter weatherListAdapter) {
        super(context);
        mContext=context;
        mWeatherListAdapter=weatherListAdapter;
        View view= LayoutInflater.from(context).inflate(R.layout.lay_weatherforecastview,this);
        mImageView= (ImageView) view.findViewById(R.id.imageView);
        mTxtDate= (TextView) view.findViewById(R.id.txtDate);
        mTxtDegC= (TextView) view.findViewById(R.id.txtDegC);
        mTxtCloudiness= (TextView) view.findViewById(R.id.txtCloudiness);
    }

    public void setWeatherView(ArrayList<WeatherForecast> weatherForecasts,WeatherForecast weatherView){
        mWeatherForecast=weatherView;
        mWeatherForecasts=weatherForecasts;
        //your image url
        String url = "http://openweathermap.org/img/w/"+mWeatherForecast.getImageIcon()+".png";
        Picasso.with(mContext).load(url).into(mImageView);
        mTxtDate.setText(mWeatherForecast.getDate().toString());
        mTxtDegC.setText("" +weatherView.getTemp()+"ºC");
        mTxtCloudiness.setText(""+weatherView.getCloudiness());
    }
}
