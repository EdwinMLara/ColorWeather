package com.example.emlar.colorweather;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    public final static String TAG = MainActivity.class.getSimpleName();

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

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://www.google.com";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        Log.d(TAG,"Response is: "+response.substring(0,5000));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG,"that didnt work: ");
            }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);

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
