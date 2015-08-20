package com.example.omkar.weatherforecast.entity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;

/**
 * Created by Omkar on 13-Aug-15.
 */
public class WeatherForecast implements Serializable {

    private String mCityName;    //pune
    private double mLag,mLat;
    private double mTemp,mPressure;    //295.34K 952.2hpa
    private int mHumidity;            //100%
    private String mCloudiness;         //moderate rain
    private double mSpeed,mRain;         //2.13 m/s   8.23
    private double mClouds,mDeg;         //92%      273
    private String mDate;
    private  int mImageID;
    private String mImageIcon;
    private String mBitmap;

    public WeatherForecast(){

    }

    public WeatherForecast(String imageIcon,int imgID,String cityName, double lag, double lat, double temp, double pressure,
                           int humidity, String cloudiness, double speed, double rain, double clouds, double deg,String date) {
        this.mImageIcon=imageIcon;
        this.mImageID=imgID;
        this.mCityName = cityName;
        mLag = lag;
        this.mLat = lat;
        this.mTemp = temp;
        this.mPressure = pressure;
        this.mHumidity = humidity;
        this.mCloudiness = cloudiness;
        this.mSpeed = speed;
        this.mRain = rain;
        this.mClouds = clouds;
        this.mDeg = deg;
        this.mDate=date;
    }

    public String getBitmap() {
        return mBitmap;
    }

    public void setBitmap(String bitmap) {
        this.mBitmap = bitmap;
    }

    public String getImageIcon() {
        return mImageIcon;
    }

    public void setImageIcon(String imageIcon) {
        this.mImageIcon = imageIcon;
    }

    public int getImageID() {
        return mImageID;
    }

    public void setImageID(int imageID) {
        this.mImageID = imageID;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        this.mDate = date;
    }

    public String getCityName() {
        return mCityName;
    }

    public void setCityName(String cityName) {
        this.mCityName = cityName;
    }

    public double getLag() {
        return mLag;
    }

    public void setLag(double lag) {
        mLag = lag;
    }

    public double getLat() {
        return mLat;
    }

    public void setLat(double lat) {
        this.mLat = lat;
    }

    public double getTemp() {
        return mTemp;
    }

    public void setTemp(double temp) {
        this.mTemp = temp;
    }

    public double getPressure() {
        return mPressure;
    }

    public void setPressure(double pressure) {
        this.mPressure = pressure;
    }

    public int getHumidity() {
        return mHumidity;
    }

    public void setHumidity(int humidity) {
        this.mHumidity = humidity;
    }

    public String getCloudiness() {
        return mCloudiness;
    }

    public void setCloudiness(String cloudiness) {
        this.mCloudiness = cloudiness;
    }

    public double getSpeed() {
        return mSpeed;
    }

    public void setSpeed(double speed) {
        this.mSpeed = speed;
    }

    public double getRain() {
        return mRain;
    }

    public void setRain(double rain) {
        this.mRain = rain;
    }

    public double getClouds() {
        return mClouds;
    }

    public void setClouds(double clouds) {
        this.mClouds = clouds;
    }

    public double getDeg() {
        return mDeg;
    }

    public void setDeg(double deg) {
        this.mDeg = deg;
    }


    public String BitMapToString(Bitmap bitmap) {
        ByteArrayOutputStream ByteStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, ByteStream);
        byte[] b = ByteStream.toByteArray();
        String temp = Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }

    public Bitmap StringToBitMap(String encodedString) {
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }
}
