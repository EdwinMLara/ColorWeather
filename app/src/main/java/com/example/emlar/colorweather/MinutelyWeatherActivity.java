package com.example.emlar.colorweather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.emlar.colorweather.Adapters.MinutelyWeatherAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MinutelyWeatherActivity extends AppCompatActivity {
    @BindView(R.id.minutelyRecyclerView) RecyclerView minutelyRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minutely_weather);
        ButterKnife.bind(this);

        ArrayList<Minute> minutes = new ArrayList<Minute>();
        Minute minute = new Minute();
        minute.setTitle("19.55");
        minute.setRainProbability("99.9%");

        Minute minute2 = new Minute();
        minute2.setTitle("15.55");
        minute2.setRainProbability("99.9%");


        minutes.add(minute);
        minutes.add(minute2);

        MinutelyWeatherAdapter minutelyWeatherAdapter = new MinutelyWeatherAdapter(this,minutes);
        minutelyRecyclerView.setAdapter(minutelyWeatherAdapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        minutelyRecyclerView.setLayoutManager(layoutManager);

        minutelyRecyclerView.setHasFixedSize(true);
    }
}
