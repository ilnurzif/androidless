package com.naura.less.theatherdata;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.naura.less.R;

import java.util.List;

public class TheatherWeekAdapter  extends RecyclerView.Adapter<TheatherWeekAdapter.ViewHolder>  {
   private List<TheatherData> theatherdays;
    private LayoutInflater inflater;

    public TheatherWeekAdapter(Context context, List<TheatherData> theatherdays) {
        this.theatherdays = theatherdays;
        this.inflater=LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public TheatherWeekAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TheatherWeekAdapter.ViewHolder holder, int position) {
        TheatherData theatherDay=theatherdays.get(position);
        holder.weatherView.setImageResource(theatherDay.getTheathericon());
        holder.temperatureView.setText(theatherDay.getTemperature());
        holder.weekdayView.setText(theatherDay.getWeekday());
    }

    @Override
    public int getItemCount() {
        return theatherdays.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView weatherView;
        final TextView weekdayView, temperatureView;
        ViewHolder(View view){
            super(view);
            weatherView = (ImageView)view.findViewById(R.id.weathericon);
            weekdayView = (TextView) view.findViewById(R.id.weekday);
            temperatureView = (TextView) view.findViewById(R.id.temperature);
        }
    }
}
