package com.naura.less.citydetail;

import android.content.res.Configuration;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.appcompat.widget.Toolbar;

import com.naura.less.R;
import com.naura.less.appsettings.BaseActivity;
import com.naura.less.citylist.CityLoader;
import com.naura.less.observercode.EventsConst;
import com.naura.less.observercode.Observer;

public class CityDetailActivity extends BaseActivity implements Observer {
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

    @Override
    public <T> void update(String eventName, T val) {
        if (eventName.equals(EventsConst.selectCityEvent)) {
            dataLoad((String) val);
        }
    }
}
