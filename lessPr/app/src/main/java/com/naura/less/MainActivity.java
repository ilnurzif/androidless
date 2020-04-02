package com.naura.less;

import android.content.res.Configuration;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;


import com.naura.less.appsettings.BaseActivity;
import com.naura.less.observercode.EventsConst;
import com.naura.less.observercode.Observable;
import com.naura.less.observercode.Observer;
import com.naura.less.citydetail.CityData;
import com.naura.less.citylist.CityLoader;

public class MainActivity extends BaseActivity implements Observer {
    private androidx.coordinatorlayout.widget.CoordinatorLayout mainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.city);
        initVisual();
        dataLoad(CityLoader.getDefaultCityName(this));
    }

    private void initVisual() {
        mainLayout = findViewById(R.id.mainlayout);
        Observable observable = Observable.getInstance();
        observable.subscribe(this);
    }

    @Override
    public <T> void update(String eventName, T val) {
        if (eventName.equals(EventsConst.selectCityEvent)) {
            dataLoad((String) val);
        }
    }

    private void dataLoad(String cityName) {
        CityData cityData = CityLoader.getCity(this, cityName);
        Drawable drawable = new BitmapDrawable(cityData.getVerticalImage());
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
            mainLayout.setBackground(drawable);
        else
            mainLayout.setBackground(new BitmapDrawable(cityData.getHorisontalImage()));
    }
}
