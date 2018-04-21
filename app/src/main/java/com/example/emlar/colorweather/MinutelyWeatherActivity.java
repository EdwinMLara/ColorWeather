package com.example.emlar.colorweather;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.emlar.colorweather.Adapters.MinutelyWeatherAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MinutelyWeatherActivity extends AppCompatActivity {
    @BindView(R.id.minutelyRecyclerView) RecyclerView minutelyRecyclerView;
    @BindView(R.id.reciclerViewNoDataTextView) TextView reciclerViewNoDataTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minutely_weather);
        ButterKnife.bind(this);

        Intent intent = getIntent();

        ArrayList<Minute> minutes = intent.getParcelableArrayListExtra(MainActivity.MINUTE_ARRAY_LIST);

        if (minutes != null && !minutes.isEmpty()) {
            reciclerViewNoDataTextView.setVisibility(View.GONE);
            minutelyRecyclerView.setVisibility(View.VISIBLE);

            MinutelyWeatherAdapter minutelyWeatherAdapter = new MinutelyWeatherAdapter(this, minutes);
            minutelyRecyclerView.setAdapter(minutelyWeatherAdapter);

            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
            minutelyRecyclerView.setLayoutManager(layoutManager);

            minutelyRecyclerView.setHasFixedSize(true);
        }else{
            reciclerViewNoDataTextView.setVisibility(View.VISIBLE);
            minutelyRecyclerView.setVisibility(View.GONE);
        }
    }
}
