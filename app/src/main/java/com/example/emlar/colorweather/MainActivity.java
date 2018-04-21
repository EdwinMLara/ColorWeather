package com.example.emlar.colorweather;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    public final static String TAG = MainActivity.class.getSimpleName();
    public static final String DATA = "data";
    public static final String SUMMARY = "summary";
    public static final String ICON = "icon";
    public static final String TEMPERATURE = "temperature";
    public static final String TEMPERATURE_MAX = "temperatureMax";
    public static final String TEMPERATURE_MIN = "temperatureMin";
    public static final String PRECIP_PROBABILITY = "precipProbability";
    public static final String TIME = "time";
    public static final String CURRENTLY = "currently";
    public static final String DAILY = "daily";
    public static final String HOURLY = "hourly";
    public static final String MINUTELY = "minutely";
    public static final String TIMEZONE = "timezone";
    public static final String DAYS_ARRAY_LIST = "DAYS";
    public static final String HOUR_ARRAY_LIST = "HOUR_ARRAY_LIST";
    public static final String MINUTE_ARRAY_LIST = "MINUTE_ARRAY_LIST";

    private FusedLocationProviderClient myLocationClient;
    private static final int MY_PERMISSION_RERQUEST_FINE_LOCATION = 101;

    @BindView(R.id.DailyWeatherTextView)
    TextView DailyWeatherTextView;
    @BindView(R.id.HourlyWeatherTextView)
    TextView HourlyWeatherTextView;
    @BindView(R.id.MinutelyWeatherTextView)
    TextView MinutelyWeatherTextView;
    @BindView(R.id.IconImageView)
    ImageView IconImageView;
    @BindView(R.id.DescriptionTextView)
    TextView DescriptionTextView;
    @BindView(R.id.LowestTempTextView)
    TextView LowestTextView;
    @BindView(R.id.HighestTempTextView)
    TextView HighestTextView;
    @BindView(R.id.CurrentTempTextView)
    TextView CurrentTempTextView;

    ArrayList<Day> days;
    ArrayList<Hour> hours;
    ArrayList<Minute> minutes;

    public String latitude;
    public String longuitud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        String forecastURL = "https://api.darksky.net/forecast";
        String apiKey = "b80442124a6fad38da9432bb22246b6c";
        String units = "units=si";

        latitude = "37.8267";
        longuitud = "-122.4233";


        myLocationClient = LocationServices.getFusedLocationProviderClient(this);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            myLocationClient.getLastLocation()
                    .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            // Got last known location. In some rare situations this can be null.
                            if (location != null) {
                                latitude = String.valueOf(location.getLatitude());
                                longuitud = String.valueOf(location.getLongitude());
                                Toast.makeText(MainActivity.this,latitude+", "+longuitud, Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(MainActivity.this, "Fallo", Toast.LENGTH_LONG).show();
                                latitude = "37.8267";
                                longuitud = "-122.4233";
                            }

                        }
                    });
        }else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},MY_PERMISSION_RERQUEST_FINE_LOCATION);
            }
        }


        RequestQueue queue = Volley.newRequestQueue(this);
        String url = forecastURL + "/" + apiKey + "/" + latitude + "," + longuitud + "?" + units;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            CurrentWeather currentWeather1 = getCurrentWeather(response);
                            days = getDailyWeatherFromJson(response);
                            hours = getHourlyWeatherFromJson(response);
                            minutes = getMinutelyWeatherFromJson(response);

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
                Toast.makeText(MainActivity.this,"Connection error",Toast.LENGTH_LONG).show();
            }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case MY_PERMISSION_RERQUEST_FINE_LOCATION:

                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permision was granted do nothing and carry on;
                }else{
                    Toast.makeText(getApplicationContext(),"this app requieres pemission to be grandted", Toast.LENGTH_LONG).show();
                    finish();
                }
                break;
        }
    }

    @OnClick(R.id.DailyWeatherTextView)
    public void DailyWeatherClick(){
        Intent DailyWeatherIntend = new Intent(MainActivity.this,DailyWeatherActivity.class);
        DailyWeatherIntend.putParcelableArrayListExtra(DAYS_ARRAY_LIST,days);
        startActivity(DailyWeatherIntend);
    }

    @OnClick(R.id.HourlyWeatherTextView)
    public void HourlyWeatherClick(){
        Intent HourlyWeatherIntend = new Intent(MainActivity.this,HourlyWatherActivity.class);
        HourlyWeatherIntend.putParcelableArrayListExtra(HOUR_ARRAY_LIST,hours);
        startActivity(HourlyWeatherIntend);
    }

    @OnClick(R.id.MinutelyWeatherTextView)
    public void MinutelyWeatherClick(){
        Intent MinutelyWeatherIntent = new Intent(MainActivity.this,MinutelyWeatherActivity.class);
        MinutelyWeatherIntent.putParcelableArrayListExtra(MINUTE_ARRAY_LIST,minutes);
        startActivity(MinutelyWeatherIntent);
    }

    private CurrentWeather getCurrentWeather(String json) throws JSONException{
        JSONObject jsonObject = new JSONObject(json);

        JSONObject jsonWithCurrentWeather = jsonObject.getJSONObject(CURRENTLY);
        JSONObject jsonWithDailyWeather = jsonObject.getJSONObject(DAILY);
        JSONArray jsonwithDailyWeatherData = jsonWithDailyWeather.getJSONArray(DATA);
        JSONObject jsonWtihTodayData = jsonwithDailyWeatherData.getJSONObject(0);


        String summary =  jsonWithCurrentWeather.getString(SUMMARY);
        String icon = jsonWithCurrentWeather.getString(ICON);
        String temperature = Math.round(Float.parseFloat(jsonWithCurrentWeather.getString(TEMPERATURE))) + "" ;

        String maxTemperature = Math.round(jsonWtihTodayData.getDouble(TEMPERATURE_MAX)) + "";
        String minTemperature = Math.round(jsonWtihTodayData.getDouble(TEMPERATURE_MIN)) + "";

        CurrentWeather currentWeather = new CurrentWeather(MainActivity.this);

        currentWeather.setDescription(summary);
        currentWeather.setIconimage(icon);
        currentWeather.setCurrentTemperature(temperature);
        currentWeather.setHighestTemperature(maxTemperature);
        currentWeather.setLowestTemperature(minTemperature);

        return  currentWeather;

    }

    private ArrayList<Day> getDailyWeatherFromJson(String json) throws JSONException {

        //sirve para darle formateo ala hora que se obtiene el json en milisegundo trascurridos
        //toma el formato de del patron de la cadena
        DateFormat dateFormat = new SimpleDateFormat("EEEE");

        ArrayList<Day> days = new ArrayList<Day>();

        JSONObject jsonObject = new JSONObject(json);

        String timeZone = jsonObject.getString(TIMEZONE);
        dateFormat.setTimeZone(TimeZone.getTimeZone(timeZone));

        JSONObject jsonWithDailyWeather = jsonObject.getJSONObject(DAILY);
        JSONArray jsonwithDailyWeatherData = jsonWithDailyWeather.getJSONArray(DATA);

        for (int i =0;i<jsonwithDailyWeatherData.length();i++){
            Day day = new Day();

            JSONObject  jsonWithDayData = jsonwithDailyWeatherData.getJSONObject(i);
            String rainProbability = "Rain Probability: " + jsonWithDayData.getDouble(PRECIP_PROBABILITY)*100 + "%";
            String Description = jsonWithDayData.getString(SUMMARY);
            String dataName = dateFormat.format(jsonWithDayData.getDouble(TIME)*1000);

            day.setDayName(dataName);
            day.setRainProbability(rainProbability);
            day.setWeatherDayDescription(Description);

            days.add(day);
        }

        return days;
    }

    private ArrayList<Hour> getHourlyWeatherFromJson(String json) throws JSONException{

        DateFormat dateFormat = new SimpleDateFormat("HH:mm");

        ArrayList<Hour> hours = new ArrayList<Hour>();

        JSONObject jsonObject = new JSONObject(json);

        String timeZone = jsonObject.getString(TIMEZONE);
        dateFormat.setTimeZone(TimeZone.getTimeZone(timeZone));

        JSONObject jsonWithHourlyWeather = jsonObject.getJSONObject(HOURLY);
        JSONArray jsonwithHourlyWeatherData = jsonWithHourlyWeather.getJSONArray(DATA);

        for (int i=0;i<jsonwithHourlyWeatherData.length();i++){
            Hour hour = new Hour();

            JSONObject jsonWithHourData = jsonwithHourlyWeatherData.getJSONObject(i);

            String title = dateFormat.format(jsonWithHourData.getDouble(TIME)*1000);
            String description = jsonWithHourData.getString(SUMMARY);

            hour.setTitle(title);
            hour.setWeatherDescription(description);

            hours.add(hour);
        }
        return hours;
    }

    private ArrayList<Minute> getMinutelyWeatherFromJson (String json) throws JSONException{
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");

        ArrayList<Minute> minutes = new ArrayList<Minute>();

        JSONObject jsonObject = new JSONObject(json);

        String timeZone = jsonObject.getString(TIMEZONE);
        dateFormat.setTimeZone(TimeZone.getTimeZone(timeZone));

        JSONObject jsonWithMinutelyWeather = jsonObject.getJSONObject(MINUTELY);
        JSONArray jsonwithMinutelyWeatherData = jsonWithMinutelyWeather.getJSONArray(DATA);

        for(int i=0;i<jsonwithMinutelyWeatherData.length();i++){
            Minute minute = new Minute();

            JSONObject jsonWithMinuteData = jsonwithMinutelyWeatherData.getJSONObject(i);
            String title = dateFormat.format(jsonWithMinuteData.getDouble(TIME)*1000);
            String rainProbability = "Rain Probability: " + jsonWithMinuteData.getDouble(PRECIP_PROBABILITY)*100 + "%";

            minute.setTitle(title);
            minute.setRainProbability(rainProbability);

            minutes.add(minute);
        }
        return minutes;
    }
}
