package com.example.omkar.weatherforecast.thread;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;

import com.example.omkar.weatherforecast.entity.WeatherForecast;
import com.example.omkar.weatherforecast.utility.WebUtility;

import java.util.ArrayList;

/**
 * Created by Omkar on 13-Aug-15.
 */
public class WeatherforecastLoader extends AsyncTask<String,Object,ArrayList> {

    private ProgressDialog mProgressDialog;
    private Context mContext;
    private Handler mHandler;

    public WeatherforecastLoader(Context context, Handler handler) {
        this.mContext = context;
        this.mHandler = handler;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        mProgressDialog = new ProgressDialog(mContext);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressDialog.setTitle("Loading...");
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.show();
    }

    @Override
    protected ArrayList doInBackground(String... params) {
        ArrayList<WeatherForecast> weatherForecasts= WebUtility.getContent(params[0]);
        return weatherForecasts;
    }

    @Override
    protected void onPostExecute(ArrayList arrayList) {
        super.onPostExecute(arrayList);
        mProgressDialog.dismiss();
        Message message = new Message();
        message.obj = arrayList;
        mHandler.sendMessage(message);

    }

    @Override
    protected void onProgressUpdate(Object... values) {
        super.onProgressUpdate(values);
    }
}
