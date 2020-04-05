package com.naura.less.citydetail;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.naura.less.R;
import com.naura.less.citylist.OpenWeatherMapLoader;
import com.naura.less.observercode.EventsConst;
import com.naura.less.observercode.Observable;
import com.naura.less.observercode.Observer;
import com.naura.less.citylist.CityLoader;
import com.naura.less.theatherdata.TheatherData;
import com.naura.less.theatherdata.TheatherWeekAdapter;

import java.util.ArrayList;
import java.util.List;

public class CityDetailFragment extends Fragment implements Observer {
    private RecyclerView recyclerView;
    private TextView temperatureTextView;
    private TextView airhumidityTextView;
    private List<TheatherData> theatherDays = new ArrayList<>();
    private TheatherWeekAdapter adapter;
    private CityLoader cityLoader;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.city_detail_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
    }

    private void initViews(View view) {
        recyclerView = view.findViewById(R.id.weekdays);
        temperatureTextView = view.findViewById(R.id.temperaturetextView);
        airhumidityTextView = view.findViewById(R.id.airhumiditytextView);

        Observable observable = Observable.getInstance();
        observable.subscribe(this);

        cityLoader = OpenWeatherMapLoader.getInstance(getActivity());
        cityLoader.startLoad();
    }

    private void dataLoad(String cityName, List<TheatherData> theatherDays) {
        if (adapter == null) {
            adapter = new TheatherWeekAdapter(getActivity(), theatherDays);
            recyclerView.setAdapter(adapter);
        } else {
            adapter.setTheatherDays(theatherDays);
            adapter.notifyDataSetChanged();
        }

        String temperatureNow = theatherDays.get(0).getTemperature();
        temperatureTextView.setText(temperatureNow);

        String airhumidity = theatherDays.get(0).getAirhumidity();
        airhumidity = "Влажность " + airhumidity;
        airhumidity = airhumidity;

        airhumidityTextView.setText(airhumidity);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public <T> void update(String eventName, T val) {
        if (eventName.equals(EventsConst.selectCityEvent)) {
            cityLoader.setDefaultCityName((String) val);
            cityLoader.startLoad();
            if (getActivity() == null) return;
        }
        if (eventName.equals(EventsConst.cityLoadFinish)) {
            List<TheatherData> cityTheatherList = (List<TheatherData>) val;
            dataLoad(cityLoader.getDefaultCityName(), cityTheatherList);
        }
    }
}
