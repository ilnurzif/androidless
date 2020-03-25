package com.naura.less;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.res.Configuration;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import android.widget.LinearLayout;

import com.naura.less.observercode.EventsConst;
import com.naura.less.observercode.Observable;
import com.naura.less.observercode.Observer;
import com.naura.less.citydetail.CityData;
import com.naura.less.citylist.CityLoader;


public class MainActivity extends AppCompatActivity implements Observer {
    private Toolbar toolbar;
    private LinearLayout mainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.city_choice);
        initVisual();
        dataLoad(CityLoader.getDefaultCityName(this));
    }

    private void initVisual() {
        toolbar = findViewById(R.id.toolbar);
        toolbar.setSubtitle(R.string.title_activity_city_list);
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
        toolbar.setSubtitle(cityName);
        CityData cityData = CityLoader.getCity(this, cityName);
        Drawable drawable = new BitmapDrawable(cityData.getVerticalImage());
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
            mainLayout.setBackground(drawable);
        else
            mainLayout.setBackground(new BitmapDrawable(cityData.getHorisontalImage()));
    }
}
