package com.naura.less.citylist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.naura.less.R;
import com.naura.less.citydetail.CityData;

import java.util.ArrayList;
import java.util.List;

public class CityListFragment extends Fragment {
    private List<CityData> cityList;
    private RecyclerView recyclerView;

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
        cityList = CityLoader.getCityList(getActivity());
        CityListAdapter adapter = new CityListAdapter(getActivity(), cityList);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }
}
