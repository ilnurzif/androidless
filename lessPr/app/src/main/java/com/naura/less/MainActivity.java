package com.naura.less;

import android.content.res.Configuration;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;


import com.naura.less.appsettings.BaseActivity;
import com.naura.less.citylist.OpenWeatherMapLoader;
import com.naura.less.observercode.EventsConst;
import com.naura.less.observercode.Observable;
import com.naura.less.observercode.Observer;
import com.naura.less.citydetail.CityData;
import com.naura.less.citylist.CityLoader;

public class MainActivity extends BaseActivity implements Observer {
    private androidx.coordinatorlayout.widget.CoordinatorLayout mainLayout;
    private CityLoader cityLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.city);
        initVisual();
    }

    private void initVisual() {
        mainLayout = findViewById(R.id.mainlayout);
        Observable observable = Observable.getInstance();
        observable.subscribe(this);
        cityLoader = OpenWeatherMapLoader.getInstance(this);
    }

    @Override
    public <T> void update(String eventName, T val) {
        if (eventName.equals(EventsConst.selectCityEvent)) {
            dataLoad((String) val);
        }
    }

    private void dataLoad(String cityName) {
        CityData cityData = cityLoader.getCity(cityName);
        Drawable drawable = new BitmapDrawable(cityData.getVerticalImage());
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
            mainLayout.setBackground(drawable);
        else
            mainLayout.setBackground(new BitmapDrawable(cityData.getHorisontalImage()));
    }
}
