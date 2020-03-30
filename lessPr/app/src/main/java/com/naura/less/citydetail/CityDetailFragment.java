package com.naura.less.citydetail;

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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.city_detail_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        dataLoad(CityLoader.getDefaultCityName(getActivity()));
    }

    private void initViews(View view) {
        recyclerView = view.findViewById(R.id.weekdays);
        temperatureTextView = view.findViewById(R.id.temperaturetextView);
        airhumidityTextView = view.findViewById(R.id.airhumiditytextView);

        Observable observable = Observable.getInstance();
        observable.subscribe(this);
    }

    private void dataLoad(String cityName) {
        theatherDays = CityLoader.getTheatherData(getActivity(), cityName);
        adapter = new TheatherWeekAdapter(getActivity(), theatherDays);
        recyclerView.setAdapter(adapter);

        CityData cityData = CityLoader.getCity(getActivity(), cityName);
        if (cityData == null)
            throw new NullPointerException("city " + cityName + " not found");
        CityLoader.setDefaultCityName(cityName);

        String temperatureNow = cityData.getTemperatureNow() + "°";
        String airhumidity = getResources().getText(R.string.airhumidity) + " " + cityData.getAirhumidityNow() + " %";

        temperatureTextView.setText(temperatureNow);
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
            CityLoader.setDefaultCityName((String) val);
            if (getActivity() == null) return;
            dataLoad(CityLoader.getDefaultCityName(getActivity()));
        }
    }
}
