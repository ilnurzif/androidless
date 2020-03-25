package com.naura.less.citydetail;

import android.content.res.Configuration;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;

import com.naura.less.R;
import com.naura.less.citylist.CityLoader;

public class CityDetailActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private LinearLayout mainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.city_detail_main);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            finish();
            return;
        }

        initVisual();
        dataLoad(CityLoader.getDefaultCityName(this));
    }

    private void initVisual() {
        toolbar = findViewById(R.id.toolbar);
        mainLayout = findViewById(R.id.mainlayout);
    }

    private void dataLoad(String cityName) {
        toolbar.setSubtitle(cityName);
        CityData cityData = CityLoader.getCity(this, cityName);
        Drawable drawable = new BitmapDrawable(cityData.getVerticalImage());
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
            mainLayout.setBackground(drawable);
        else
            mainLayout.setBackground(new BitmapDrawable(cityData.getHorisontalImage()));
    }
}
