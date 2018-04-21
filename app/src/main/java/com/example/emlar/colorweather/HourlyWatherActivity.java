package com.example.emlar.colorweather;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.emlar.colorweather.Adapters.hourlyWeatherAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HourlyWatherActivity extends Activity {
    @BindView(R.id.hourlyListView) ListView hourlyListView;
    @BindView(R.id.hourlyNoDataTextView) TextView hourlyNoDataTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hourly_wather);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        ArrayList<Hour> hours = intent.getParcelableArrayListExtra(MainActivity.HOUR_ARRAY_LIST);

        hourlyWeatherAdapter hourlyWeatherAdapter1 = new hourlyWeatherAdapter(this,hours);
        hourlyListView.setAdapter(hourlyWeatherAdapter1);
        hourlyListView.setEmptyView(hourlyNoDataTextView);
    }
}
