package com.example.emlar.colorweather;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by emlar on 18/02/2018.
 */

public class Day implements Parcelable{

    private String dayName;
    private String weatherDayDescription;
    private String rainProbability;

    public Day(){}

    protected Day(Parcel in) {
        dayName = in.readString();
        weatherDayDescription = in.readString();
        rainProbability = in.readString();
    }

    public static final Creator<Day> CREATOR = new Creator<Day>() {
        @Override
        public Day createFromParcel(Parcel in) {
            return new Day(in);
        }

        @Override
        public Day[] newArray(int size) {
            return new Day[size];
        }
    };

    public String getDayName() {
        return dayName;
    }

    public void setDayName(String dayName) {
        this.dayName = dayName;
    }

    public String getRainProbability() {
        return rainProbability;
    }

    public void setRainProbability(String rainProbability) {
        this.rainProbability = rainProbability;
    }

    public String getWeatherDayDescription() {
        return weatherDayDescription;
    }

    public void setWeatherDayDescription(String weatherDayDescription) {
        this.weatherDayDescription = weatherDayDescription;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(dayName);
        parcel.writeString(weatherDayDescription);
        parcel.writeString(rainProbability);
    }
}

