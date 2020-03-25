package com.naura.less.citylist;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.naura.less.R;
import com.naura.less.observercode.EventsConst;
import com.naura.less.observercode.Observable;
import com.naura.less.citydetail.CityData;
import com.naura.less.citydetail.CityDetailActivity;

import java.util.List;

public class CityListAdapter extends RecyclerView.Adapter<CityListAdapter.ViewHolder> {
    private List<CityData> cityDataList;
    private LayoutInflater layoutInflater;
    private Context context;
    private Observable observable = Observable.getInstance();

    public CityListAdapter(Context context, List<CityData> cityDataList) {
        this.cityDataList = cityDataList;
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public CityListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.city_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CityData cityData = cityDataList.get(position);
        holder.cityNameTextView.setText(cityData.getName());
        holder.citySmallImageView.setImageBitmap(cityData.getSmallImage());
    }

    @Override
    public int getItemCount() {
        return cityDataList.size();
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    private void openCityList(Activity activity, String cityName) {
        CityLoader.setDefaultCityName(cityName);
         observable=Observable.getInstance();
         observable.notify(EventsConst.selectCityEvent, cityName);
        if (activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            return;
        }
        Intent intentcity = new Intent(activity, CityDetailActivity.class);
        intentcity.putExtra("cityname", cityName);
        activity.startActivity(intentcity);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView cityNameTextView;
        private ImageView citySmallImageView;

        ViewHolder(View view) {
            super(view);
            cityNameTextView = view.findViewById(R.id.cityNameTextView);
            citySmallImageView = view.findViewById(R.id.citySmallImageView);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Activity activity = (Activity) context;
                    openCityList(activity, cityNameTextView.getText().toString());
                }
            });
        }
    }
}
