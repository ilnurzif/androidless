package com.naura.less.citydetail;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.naura.less.R;
import com.naura.less.citylist.CityLoader;
import com.naura.less.theatherdata.TheatherData;
import com.naura.less.theatherdata.TheatherWeekAdapter;

import java.util.ArrayList;
import java.util.List;

public class CityDetailFragment extends Fragment {
    private RecyclerView recyclerView;
    private TextView temperaturetextView;
    private TextView airhumiditytextView;
    private List<TheatherData> theatherDays = new ArrayList<>();
    private TheatherWeekAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      //  return super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.city_detail_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        dataload(CityLoader.getDefaultCityName(getActivity()));
    }

    private void initViews(View view) {
        recyclerView = view.findViewById(R.id.weekdays);
        temperaturetextView = view.findViewById(R.id.temperaturetextView);
        airhumiditytextView = view.findViewById(R.id.airhumiditytextView);
    }

    private void dataload(String cityname) {
        theatherDays = CityLoader.getTheatherData(getActivity(), cityname);
        adapter = new TheatherWeekAdapter(getActivity(), theatherDays);
        recyclerView.setAdapter(adapter);
        CityData cityData = CityLoader.getCity(getActivity(), cityname);
        if (cityData == null)
            throw new NullPointerException("city " + cityname + " not found");
        CityLoader.setDefaultCityName(cityname);

        temperaturetextView.setText(cityData.getTemperatureNow() + "°");
        airhumiditytextView.setText(getResources().getText(R.string.airhumidity)+" "
                +cityData.getAirhumidityNow() + " %");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
      if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {

      }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
