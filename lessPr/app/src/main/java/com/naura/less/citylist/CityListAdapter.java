package com.naura.less.citylist;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.naura.less.MainActivity;
import com.naura.less.R;

import java.util.List;

public class CityListAdapter extends RecyclerView.Adapter<CityListAdapter.ViewHolder> {
    private List<CityData> citydatalist;
    private LayoutInflater layoutInflater;
    private Context context;

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
                    Intent intentcity = new Intent(context, MainActivity.class);
                    intentcity.putExtra("cityname", cityNameTextView.getText().toString());
                    Activity activity = (Activity) context;
                    activity.setResult(activity.RESULT_OK, intentcity);
                    activity.finish();
                }
            });
        }
    }
}
