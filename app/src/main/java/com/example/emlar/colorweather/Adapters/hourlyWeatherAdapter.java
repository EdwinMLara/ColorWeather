package com.example.emlar.colorweather.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.emlar.colorweather.Hour;
import com.example.emlar.colorweather.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by emlar on 22/02/2018.
 */

public class hourlyWeatherAdapter extends BaseAdapter{
    ArrayList<Hour> hours;
    Context context;

    public hourlyWeatherAdapter(Context context, ArrayList<Hour> hours){
        this.context = context;
        this.hours = hours;
    }

    @Override
    public int getCount() {
        return hours.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder ;
        Hour hour = hours.get(i);
        if(view==null){
            view = LayoutInflater.from(context).inflate(R.layout.hourly_list_item,viewGroup,false);
            viewHolder = new ViewHolder();
            viewHolder.title = view.findViewById(R.id.hourlyTitleTextView);
            viewHolder.description = view.findViewById(R.id.hourlyDescriptionTextView);

            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.title.setText(hour.getTitle());
        viewHolder.description.setText((CharSequence) hour.getWeatherDescription());

        return view;
    }

    static class ViewHolder{
        TextView title;
        TextView description;
    }
}
