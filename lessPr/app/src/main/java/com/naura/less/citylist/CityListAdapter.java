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

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
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
    private int currentPosition = -1;
    private Boolean cardMode = false;
    private Observable observable = Observable.getInstance();
    private CityLoader cityLoader;

    public CityListAdapter(Context context, List<CityData> cityDataList, Boolean cardMode) {
        this.cityDataList = cityDataList;
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
        this.cardMode = cardMode;
        this.cityLoader = OpenWeatherMapLoader.getInstance(context);
    }

    public void setCityDataList(List<CityData> cityDataList) {
        this.cityDataList = cityDataList;
    }

    @NonNull
    @Override
    public CityListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view;
        if (cardMode) {
            view = LayoutInflater.from(context)
                    .inflate(R.layout.city_item_card, parent, false);
        } else {
            view = LayoutInflater.from(context)
                    .inflate(R.layout.city_item, parent, false);
        }
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CityData cityData = cityDataList.get(position);
        holder.cityNameTextView.setText(cityData.getName());
        if (cityData.isFavoriteCity()) {
            holder.citySmallImageView.setBackground(context.getResources().getDrawable(R.drawable.like_red));
        } else
            holder.citySmallImageView.setBackground(context.getResources().getDrawable(R.drawable.like_brown));
        if (cardMode) {
            holder.bigImage.setImageBitmap(cityData.getVerticalImage());
            holder.citySmallImageView.setBackground(context.getResources().getDrawable(R.drawable.like_red));
        }

        SetOnClickHolder(holder, position);
        repaintView(holder, position);
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
        cityLoader.setDefaultCityName(cityName);
        observable = Observable.getInstance();
        observable.notify(EventsConst.selectCityEvent, cityName);
        if (activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            return;
        }
        Intent intentcity = new Intent(activity, CityDetailActivity.class);
        intentcity.putExtra("cityname", cityName);
        activity.startActivity(intentcity);
    }

    private void SetOnClickHolder(@NonNull final ViewHolder holder, final int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.cityNameTextView = view.findViewById(R.id.cityNameTextView);
                holder.citySmallImageView = view.findViewById(R.id.citySmallImageView);
                if (cardMode) {
                    holder.bigImage = view.findViewById(R.id.cityBigImageView);
                }
                currentPosition = position;
                notifyDataSetChanged();
                Activity activity = (Activity) context;
                openCityList(activity, holder.cityNameTextView.getText().toString());
            }
        });
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView cityNameTextView;
        ImageView citySmallImageView;
        ImageView bigImage;
        CardView cityCardView;

        ViewHolder(View view) {
            super(view);
            cityNameTextView = view.findViewById(R.id.cityNameTextView);
            citySmallImageView = view.findViewById(R.id.citySmallImageView);
            citySmallImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final String cityName = cityNameTextView.getText().toString();
                    cityLoader.SetlikeCity(cityName);
                    BottomNavigationView bottomBar = ((Activity) context).findViewById(R.id.nav_view);
                    Snackbar.make(citySmallImageView, R.string.city_add_question, Snackbar.LENGTH_LONG).
                            setAction(R.string.Yes_const, new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    observable.notify(EventsConst.likeSelectEvent, cityName);
                                }
                            }).setAnchorView(bottomBar)
                            .show();
                }
            });

            if (cardMode) {
                bigImage = view.findViewById(R.id.cityBigImageView);
            }
        }
    }

    private void repaintView(@NonNull ViewHolder holder, int position) {
        if (!cardMode) {
            int color = ContextCompat.getColor(context, android.R.color.transparent);
            if (currentPosition == position)
                color = ContextCompat.getColor(context, R.color.colorGrid);
            holder.itemView.setBackgroundColor(color);
        }
    }
}
