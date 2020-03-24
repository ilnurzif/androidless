package com.naura.less;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import android.widget.TextView;

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
    //   setContentView(R.layout.activity_main);
        setContentView(R.layout.city_choice);
       initVisual();
     //   dataload(CityLoader.getDefaultCityName(this));
    }

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        toolbar.inflateMenu(R.menu.city_search_menu);
        return super.onCreateOptionsMenu(menu);
    }*/

  /*  @Override
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
    }*/

   /* private void openCityList() {
        Intent intentcity = new Intent(MainActivity.this, CityListActivity.class);
        startActivityForResult(intentcity, cityselectid);
    }*/

  /*  private void openAbout() {
        CityData cityData = CityLoader.getCity(this,
                CityLoader.getDefaultCityName(this));
        if (cityData==null)
           throw new NullPointerException("null cityData return");
        cityData.openInfo(this);
    }*/

    private void initVisual() {
       toolbar = findViewById(R.id.toolbar);
       setSupportActionBar(toolbar);
       getSupportActionBar().setTitle(R.string.theather);
       toolbar.setSubtitle(R.string.title_activity_city_list);
       mainLayout =findViewById(R.id.mainlayout);
    }

  /*  @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK && requestCode == cityselectid) {
            String cityName = data.getStringExtra("cityname");
            dataload(cityName);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void dataload(String cityname) {
   //     Drawable drawable= new BitmapDrawable(cityData.getVerticalImage());

*//*     if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
           mainLayout.setBackground(drawable);
           else
            mainLayout.setBackground(new BitmapDrawable(cityData.getHorisontalImage()));*//*
          toolbar.setSubtitle(cityname);
    }*/
}
