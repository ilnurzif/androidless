package com.naura.less;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.naura.less.citylist.CityData;
import com.naura.less.citylist.CityListActivity;
import com.naura.less.citylist.CityLoader;
import com.naura.less.theatherdata.TheatherData;
import com.naura.less.theatherdata.TheatherWeekAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private TheatherWeekAdapter adapter;
    private List<TheatherData> theatherDays = new ArrayList<>();
    private static int cityselectid = 2345;
    private TextView temperaturetextView;
    private TextView airhumiditytextView;
    private ConstraintLayout mainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initVisual();
        dataload(CityLoader.getDefaultCityName(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        toolbar.inflateMenu(R.menu.city_search_menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.select_city:
                openCityList();
                break;
            case R.id.city_info:
                openAbout();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void openCityList() {
        Intent intentcity = new Intent(MainActivity.this, CityListActivity.class);
        startActivityForResult(intentcity, cityselectid);
    }

    private void openAbout() {
        CityData cityData = CityLoader.getCity(this,
                CityLoader.getDefaultCityName(this));
        if (cityData==null)
           throw new NullPointerException("null cityData return");
        cityData.openInfo(this);
    }

    private void initVisual() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.theather);
        toolbar.setSubtitle(R.string.moscow);
        recyclerView = (RecyclerView) findViewById(R.id.weekdays);
        temperaturetextView = findViewById(R.id.temperaturetextView);
        airhumiditytextView = findViewById(R.id.airhumiditytextView);
        mainLayout = (ConstraintLayout) findViewById(R.id.mainlayout);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK && requestCode == cityselectid) {
            String cityName = data.getStringExtra("cityname");
            dataload(cityName);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void dataload(String cityname) {
        theatherDays = CityLoader.getTheatherData(this, cityname);
        adapter = new TheatherWeekAdapter(this, theatherDays);
        recyclerView.setAdapter(adapter);
        CityData cityData = CityLoader.getCity(this, cityname);
        if (cityData == null)
            throw new NullPointerException("city " + cityname + " not found");
        CityLoader.setDefaultCityName(cityname);


        temperaturetextView.setText(cityData.getTemperatureNow() + "°");
        airhumiditytextView.setText(getResources().getText(R.string.airhumidity)+" "
                                    +cityData.getAirhumidityNow() + " %");

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
            mainLayout.setBackground(new BitmapDrawable(cityData.getVerticalImage()));
        else
            mainLayout.setBackground(new BitmapDrawable(cityData.getHorisontalImage()));
        toolbar.setSubtitle(cityname);
    }
}
