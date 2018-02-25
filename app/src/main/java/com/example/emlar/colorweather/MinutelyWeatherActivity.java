package com.example.emlar.colorweather;

import android.content.Intent;
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

        Intent intent = getIntent();

        ArrayList<Minute> minutes = intent.getParcelableArrayListExtra(MainActivity.MINUTE_ARRAY_LIST);

        MinutelyWeatherAdapter minutelyWeatherAdapter = new MinutelyWeatherAdapter(this,minutes);
        minutelyRecyclerView.setAdapter(minutelyWeatherAdapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        minutelyRecyclerView.setLayoutManager(layoutManager);

        minutelyRecyclerView.setHasFixedSize(true);
    }
}
