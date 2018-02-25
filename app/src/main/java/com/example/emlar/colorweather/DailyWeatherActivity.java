package com.example.emlar.colorweather;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.emlar.colorweather.Adapters.dailyWeatherAdapter;

import java.util.ArrayList;

public class DailyWeatherActivity extends ListActivity {

    public static final String TAG = DailyWeatherActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_weather);

        Intent intent = getIntent();

        ArrayList<Day> days = intent.getParcelableArrayListExtra(MainActivity.DAYS_ARRAY_LIST);

        dailyWeatherAdapter dailyWeatherAdapter = new dailyWeatherAdapter(this,days);
        setListAdapter(dailyWeatherAdapter);
    }

}
