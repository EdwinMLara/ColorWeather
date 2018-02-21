package com.example.emlar.colorweather.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.emlar.colorweather.Day;
import com.example.emlar.colorweather.R;

import java.util.ArrayList;

/**
 * Created by emlar on 18/02/2018.
 */

public class dailyWeatherAdapter extends BaseAdapter {

    public static final String Tag = dailyWeatherAdapter.class.getSimpleName();

    ArrayList<Day> days;
    Context context;

    public dailyWeatherAdapter(Context context,ArrayList<Day> days){
        this.context = context;
        this.days = days;
    }

    @Override
    public int getCount() {
        return days.size();
    }

    @Override
    public Object getItem(int position) {
        return days.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        Day day = days.get(i);
        
        if(view==null) {

            view = LayoutInflater.from(context).inflate(R.layout.daily_list_item,viewGroup,false);
        }
        TextView dayTitle =  view.findViewById(R.id.dailyListTitle);
        TextView dayDescription =  view.findViewById(R.id.dailyListDescription);
        TextView dayRainProbability = view.findViewById(R.id.dailyListProbability);

        dayTitle.setText(day.getDayName());
        dayDescription.setText(day.getWeatherDayDescription());
        dayRainProbability.setText(day.getRainProbability());
        return view;
    }
}
