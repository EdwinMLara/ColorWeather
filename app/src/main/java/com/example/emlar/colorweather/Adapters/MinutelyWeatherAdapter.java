package com.example.emlar.colorweather.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.emlar.colorweather.Minute;
import com.example.emlar.colorweather.R;

import java.util.ArrayList;

/**
 * Created by emlar on 23/02/2018.
 */

public class MinutelyWeatherAdapter extends RecyclerView.Adapter {

    Context context;
    ArrayList<Minute> minutes;

    public MinutelyWeatherAdapter(Context context,ArrayList<Minute> minutes){
        this.context = context;
        this.minutes = minutes;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.minutely_list_item,parent,false);

        MinutelyViewHolder minutelyViewHolder = new MinutelyViewHolder(view);

        return minutelyViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((MinutelyViewHolder)holder).onBind(position);
    }

    @Override
    public int getItemCount() {
        return minutes.size();
    }

    class MinutelyViewHolder extends RecyclerView.ViewHolder{

        TextView titleTextView;
        TextView minutelyRainprobabilityTextView;
        public MinutelyViewHolder(View itemView) {
            super(itemView);

            titleTextView = itemView.findViewById(R.id.minutelyTitleTextView);
            minutelyRainprobabilityTextView = itemView.findViewById(R.id.minutelyRainProbabilityTextView);
        }

        public void onBind(int position){
            Minute minute = minutes.get(position);
            titleTextView.setText(minute.getTitle());
            minutelyRainprobabilityTextView.setText(minute.getRainProbability());
        }
    }
}
