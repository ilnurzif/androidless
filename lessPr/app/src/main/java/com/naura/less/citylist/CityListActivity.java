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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.city_choice);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initVisual();
    }

    private void initVisual() {
        citylist = new ArrayList<>();
        dataload();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.citiesRecyclerView);
        CityListAdapter adapter = new CityListAdapter(this, citylist);
        recyclerView.setAdapter(adapter);
    }

    private void dataload() {
        List<TheatherData> moscowTheatherList = new ArrayList<>();
        moscowTheatherList.add(new TheatherData("10°", getString(R.string.Monday), R.drawable.kweather));
        moscowTheatherList.add(new TheatherData("13°", getString(R.string.Thursday), R.drawable.kweather));
        moscowTheatherList.add(new TheatherData("17°", getString(R.string.Saturday), R.drawable.kweather));

        citylist.add(new CityData("Moscow",
                ResToBitmap(R.drawable.moscowsity),
                ResToBitmap(R.drawable.moscowhorizont),
                ResToBitmap(R.drawable.kweather)));

        List<TheatherData> kazanTheatherList = new ArrayList<>();
        kazanTheatherList.add(new TheatherData("17°", getString(R.string.Monday), R.drawable.kweather));
        kazanTheatherList.add(new TheatherData("16", getString(R.string.Thursday), R.drawable.kweather));
        kazanTheatherList.add(new TheatherData("17°", getString(R.string.Saturday), R.drawable.kweather));

        citylist.add(new CityData("Kazan",
                ResToBitmap(R.drawable.moscowsity),
                ResToBitmap(R.drawable.moscowhorizont),
                ResToBitmap(R.drawable.kweather)));
    }

    private Bitmap ResToBitmap(int resid) {
        return BitmapFactory.decodeResource(CityListActivity.this.getResources(), resid);
    }

}
