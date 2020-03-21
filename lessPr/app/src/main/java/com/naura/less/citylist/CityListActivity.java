package com.naura.less.citylist;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
;import com.naura.less.R;
import com.naura.less.theatherdata.TheatherData;

import java.util.ArrayList;
import java.util.List;

public class CityListActivity extends AppCompatActivity {
    private List<CityData> citylist;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.city_choice);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initVisual();
        dataload();
    }

    private void initVisual() {
        citylist = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.citiesRecyclerView);
    }

    private void dataload() {
        citylist = CityLoader.getCityList(this);
        CityListAdapter adapter = new CityListAdapter(this, citylist);
        recyclerView.setAdapter(adapter);
    }
}
