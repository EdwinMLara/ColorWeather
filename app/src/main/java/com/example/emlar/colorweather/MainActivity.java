package com.example.emlar.colorweather;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    public final static String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.DailyWeatherTextView) TextView DailyWeatherTextView;
    @BindView(R.id.HourlyWeatherTextView) TextView HourlyWeatherTextView;
    @BindView(R.id.MinutelyWeatherTextView) TextView MinutelyWeatherTextView;
    @BindView(R.id.IconImageView) ImageView IconImageView;
    @BindView(R.id.DescriptionTextView) TextView DescriptionTextView;
    @BindView(R.id.LowestTempTextView) TextView LowestTextView;
    @BindView(R.id.HighestTempTextView) TextView HighestTextView;
    @BindView(R.id.CurrentTempTextView) TextView CurrentTempTextView;

    @BindDrawable(R.drawable.clear_night) Drawable caliz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://api.darksky.net/forecast/b80442124a6fad38da9432bb22246b6c/37.8267,-122.4233?units=si";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            CurrentWeather currentWeather1 = getCurrentWeather(response);

                            IconImageView.setImageDrawable(currentWeather1.getIconDrawableResource());
                            DescriptionTextView.setText(currentWeather1.getDescription());
                            CurrentTempTextView.setText(currentWeather1.getCurrentTemperature());
                            LowestTextView.setText(String.format("L: %s°",currentWeather1.getLowestTemperature()));
                            HighestTextView.setText(String.format("H: %s°",currentWeather1.getHighestTemperature()));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
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

    private CurrentWeather getCurrentWeather(String json) throws JSONException{
        JSONObject jsonObject = new JSONObject(json);

        JSONObject jsonWithCurrentWeather = jsonObject.getJSONObject("currently");
        JSONObject jsonWithDailyWeather = jsonObject.getJSONObject("daily");
        JSONArray jsonwithDailyWeatherData = jsonWithDailyWeather.getJSONArray("data");
        JSONObject jsonWtihTodayData = jsonwithDailyWeatherData.getJSONObject(0);


        String summary =  jsonWithCurrentWeather.getString("summary");
        String icon = jsonWithCurrentWeather.getString("icon");
        String temperature = Math.round(Float.parseFloat(jsonWithCurrentWeather.getString("temperature"))) + "" ;

        String maxTemperature = Math.round(jsonWtihTodayData.getDouble("temperatureMax")) + "";
        String minTemperature = Math.round(jsonWtihTodayData.getDouble("temperatureMin")) + "";

        CurrentWeather currentWeather = new CurrentWeather(MainActivity.this);

        currentWeather.setDescription(summary);
        currentWeather.setIconimage(icon);
        currentWeather.setCurrentTemperature(temperature);
        currentWeather.setHighestTemperature(maxTemperature);
        currentWeather.setLowestTemperature(minTemperature);

        return  currentWeather;

    }
}
