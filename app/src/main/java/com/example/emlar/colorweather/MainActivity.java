package com.example.emlar.colorweather;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.DailyWeatherTextView) TextView DailyWeatherTextView;
    @BindView(R.id.HourlyWeatherTextView) TextView HourlyWeatherTextView;
    @BindView(R.id.MinutelyWeatherTextView) TextView MinutelyWeatherTextView;
    @BindView(R.id.IconImaeView) ImageView IconImageView;
    @BindView(R.id.DescriptionTextView) TextView DescriptionTextView;
    @BindView(R.id.LowestTempTextView) TextView LowestTextView;
    @BindView(R.id.HighestTempTextView) TextView HighestTextView;
    @BindView(R.id.CurrentTempTextView) TextView CurrentTempImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        CurrentWeather currentWeather = new CurrentWeather(MainActivity.this);

    }

    @OnClick(R.id.DailyWeatherTextView)
    public void DailyWeatherClick(){
        Intent DailyWeatherIntend = new Intent(MainActivity.this,DailyWeatherActivity.class);
        startActivity(DailyWeatherIntend);
    }

    @OnClick(R.id.HourlyWeatherTextView)
    public void HourlyWeatherClick(){
        Intent HourlyWeatherIntend = new Intent(MainActivity.this,HourlyWatherActivity.class);
        startActivity(HourlyWeatherIntend);
    }

    @OnClick(R.id.MinutelyWeatherTextView)
    public void MinutelyWeatherClick(){
        Intent MinutelyWeatherIntent = new Intent(MainActivity.this,MinutelyWeatherActivity.class);
        startActivity(MinutelyWeatherIntent);
    }
}
