package com.naura.less;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.naura.less.citylist.CityData;
import com.naura.less.citylist.CityListActivity;
import com.naura.less.theatherdata.TheatherData;
import com.naura.less.theatherdata.TheatherWeekAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    RecyclerView recyclerView;
    TheatherWeekAdapter adapter;
    List<TheatherData> theatherDays = new ArrayList<>();
    private static int cityselectid=2345;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initVisual();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        toolbar.inflateMenu(R.menu.city_search_menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case  R.id.select_city:
                openCityList();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void openCityList() {
        Toast.makeText(this,"refer", Toast.LENGTH_LONG).show();
        Intent intentcity=new Intent(MainActivity.this, CityListActivity.class);
        startActivityForResult(intentcity,cityselectid);
    }

    private void initVisual() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.theather);
        toolbar.setSubtitle(R.string.moscow);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.weekdays);
        TheatherWeekAdapter adapter = new TheatherWeekAdapter(this, theatherDays);
        recyclerView.setAdapter(adapter);
        dataload();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK){
          //  Toast.makeText(this, data.getStringExtra("cityname"),Toast.LENGTH_LONG).show();
             CityData citydata= (CityData) data.getSerializableExtra("city");
              String str=citydata.getName();
             Toast.makeText(this, str,Toast.LENGTH_LONG).show();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    private void dataload() {
//        theatherDays.add(new TheatherWeekAdapter(this, "19°", getString(R.string.Monday), R.drawable.kweather));
//        theatherDays.add(new TheatherWeekAdapter.TheatherData("10°", getString(R.string.Thursday), R.drawable.kweather));
//        theatherDays.add(new TheatherWeekAdapter.TheatherData("11°", getString(R.string.Tuesday), R.drawable.kweather));
    }
}
