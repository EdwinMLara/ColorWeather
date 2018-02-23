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
        ViewHolder viewHolder;
        Day day = days.get(i);

        if(view==null) {
            view = LayoutInflater.from(context).inflate(R.layout.daily_list_item,viewGroup,false);
            viewHolder = new ViewHolder();
            viewHolder.dayTitle = view.findViewById(R.id.dailyListTitle);
            viewHolder.dayDescription = view.findViewById(R.id.dailyListDescription);
            viewHolder.dayRainProbability = view.findViewById(R.id.dailyListProbability);

            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }


        viewHolder.dayTitle.setText(day.getDayName());
        viewHolder.dayDescription.setText(day.getWeatherDayDescription());
        viewHolder.dayRainProbability.setText(day.getRainProbability());
        return view;
    }

    static class ViewHolder{
        TextView dayTitle;
        TextView dayDescription;
        TextView dayRainProbability;
    }
}
