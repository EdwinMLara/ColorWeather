package com.example.emlar.colorweather;

/**
 * Created by emlar on 18/02/2018.
 */

public class Day {

    private String dayName;
    private String weatherDayDescription;
    private String rainProbability;

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


}

