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
import com.naura.less.basecode.Observable;
import com.naura.less.citydetail.CityData;
import com.naura.less.citydetail.CityDetailActivity;

import java.util.List;

public class CityListAdapter extends RecyclerView.Adapter<CityListAdapter.ViewHolder> {
    private List<CityData> citydatalist;
    private LayoutInflater layoutInflater;
    private Context context;
    private Observable observable = Observable.getInstance();

    public CityListAdapter(Context context, List<CityData> citydatalist) {
        this.citydatalist = citydatalist;
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
        CityData cityData = citydatalist.get(position);
        holder.cityNameTextView.setText(cityData.getName());
        holder.citySmallimageView.setImageBitmap(cityData.getSmallImage());
    }

    @Override
    public int getItemCount() {
        return citydatalist.size();
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    private void openCityList(Activity activity, String cityname) {
        CityLoader.setDefaultCityName(cityname);
        observable.notify("selectcityevent", cityname);
        if (activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            return;
        }
        Intent intentcity = new Intent(activity, CityDetailActivity.class);
        intentcity.putExtra("cityname", cityname);
        activity.startActivity(intentcity);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView cityNameTextView;
        private ImageView citySmallimageView;

        ViewHolder(View view) {
            super(view);
            cityNameTextView = (TextView) view.findViewById(R.id.cityNameTextView);
            citySmallimageView = (ImageView) view.findViewById(R.id.citySmallimageView);
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
