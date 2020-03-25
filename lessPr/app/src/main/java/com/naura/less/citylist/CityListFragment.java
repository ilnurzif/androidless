package com.naura.less.citylist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.naura.less.R;
import com.naura.less.basecode.Observable;
import com.naura.less.basecode.Observer;
import com.naura.less.citydetail.CityData;

import java.util.ArrayList;
import java.util.List;

public class CityListFragment extends Fragment implements Observer {
   private List<CityData> citylist;
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
        dataload();
    }

     private void initVisual(View view) {
            citylist = new ArrayList<>();
            recyclerView = view.findViewById(R.id.citiesRecyclerView);
       }

        private void dataload() {
            citylist = CityLoader.getCityList(getActivity());
            CityListAdapter adapter = new CityListAdapter(getActivity(), citylist);
            recyclerView.setAdapter(adapter);
            Observable observable=Observable.getInstance();
            observable.subscribe(this);
        }

    @Override
    public <T> void update(String eventName, T val) {
    }
}
