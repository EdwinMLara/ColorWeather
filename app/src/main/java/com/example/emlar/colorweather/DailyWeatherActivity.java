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

        ArrayList<String> dayArray = new ArrayList<>();

        dayArray.add("Lunes");

        ArrayAdapter<String> dayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,dayArray);
        setListAdapter(dayAdapter);
    }
}
