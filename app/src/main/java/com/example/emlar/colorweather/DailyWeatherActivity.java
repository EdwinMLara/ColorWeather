package com.example.emlar.colorweather;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class DailyWeatherActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_weather);

        Day day = new Day();
        day.setDayName("Lunes");
        day.setWeatherDayDescription("parcialmente nublado");
        day.setRainProbability("12%");

        Day day2 = new Day();
        day2.setDayName("Martes");
        day2.setWeatherDayDescription("parcialmente soleado");
        day2.setRainProbability("11%");

        ArrayList<Day> dayArray = new ArrayList<Day>();

        dayArray.add(day);
        dayArray.add(day2);

        ArrayAdapter<Day> dayAdapter = new ArrayAdapter<Day>(this,android.R.layout.simple_list_item_1,dayArray);
        setListAdapter(dayAdapter);
    }

}
