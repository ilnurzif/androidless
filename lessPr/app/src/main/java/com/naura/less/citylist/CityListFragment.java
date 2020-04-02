package com.naura.less.citylist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.naura.less.R;
import com.naura.less.citydetail.CityData;
import com.naura.less.observercode.EventsConst;
import com.naura.less.observercode.Observable;
import com.naura.less.observercode.Observer;

import java.util.ArrayList;
import java.util.List;

public class CityListFragment extends Fragment implements Observer {
    private List<CityData> cityList;
    private RecyclerView recyclerView;
    private CityListAdapter cityListAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.city_list_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initVisual(view);
        dataLoad();
    }

    private void initVisual(View view) {
        cityList = new ArrayList<>();
        recyclerView = view.findViewById(R.id.citiesRecyclerView);
    }

    private void dataLoad() {
        Observable observable = Observable.getInstance();
        observable.subscribe(this);
        cityList = CityLoader.getCityList(getActivity());
        cityListAdapter = new CityListAdapter(getActivity(), cityList, false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        DividerItemDecoration itemDecoration = new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL);
        itemDecoration.setDrawable(getActivity().getDrawable(R.drawable.separator));
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(cityListAdapter);
    }

    @Override
    public <T> void update(String eventName, T val) {
        if (eventName.equals(EventsConst.likeSelectEvent)) {
            cityListAdapter.setCityDataList(CityLoader.getCityList(getContext()));
            cityListAdapter.notifyDataSetChanged();
        }
    }
}
