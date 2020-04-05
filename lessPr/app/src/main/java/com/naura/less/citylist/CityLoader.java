package com.naura.less.citylist;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.naura.less.R;
import com.naura.less.citydetail.CityData;
import com.naura.less.observercode.Observable;
import com.naura.less.theatherdata.TheatherData;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CityLoader {
    protected Context context;
    protected List<CityData> cityList;
    private String defaultCityName = "";
    private String defaultKey = "";
    private static CityLoader cityLoader;
    protected Observable observable;

    public String getDefaultKey() {
        return defaultKey;
    }

    private void loaddata() {
        context.getString(R.string.Moscow);

        cityList = new ArrayList<>();

        List<TheatherData> kazanTheatherList = new ArrayList<>();
        cityList.add(new CityData(
                "Kazan",
                context.getString(R.string.Kazan),
                kazanTheatherList,
                ResToBitmap(R.drawable.kazanvertical),
                ResToBitmap(R.drawable.kazanhorizontal),
                ResToBitmap(R.drawable.kazan_small),
                "https://ru.wikipedia.org/wiki/%D0%9A%D0%B0%D0%B7%D0%B0%D0%BD%D1%8C"));

        List<TheatherData> moscowTheatherList = new ArrayList<>();
        cityList.add(new CityData("Moscow", context.getString(R.string.Moscow),
                moscowTheatherList,
                ResToBitmap(R.drawable.moscowsity),
                ResToBitmap(R.drawable.moscowhorizont),
                ResToBitmap(R.drawable.kazan_small),
                "https://ru.wikipedia.org/wiki/%D0%9C%D0%BE%D1%81%D0%BA%D0%B2%D0%B0"));
    }


    protected CityLoader(Context context) {
        this.context = context;
        observable = Observable.getInstance();
    }

    public void startLoad() {
    }

    protected Bitmap ResToBitmap(int resid) {
        return BitmapFactory.decodeResource(context.getResources(), resid);
    }

    private CityData findCity(String cityname) {
        for (CityData cd : cityList) {
            if (cd.getName().equals(cityname))
                return cd;
        }
        return null;
    }

    public void SetlikeCity(String cityName) {
        for (CityData cityData : cityList) {
            if (cityData.getName().equals(cityName))
                cityData.setFavoriteCity(!cityData.isFavoriteCity());
        }
    }

    public CityData getCity(String cityname) {
        if (cityList == null) {
            cityList = new ArrayList<>();
            loaddata();
        }
        return findCity(cityname);
    }

    public List<TheatherData> getTheatherData(Context context, String cityname) {
        CityData cityData = getCity(cityname);
        return (cityData == null) ? null : cityData.getTheatherDays();
    }

    public List<CityData> getCityList() {
        if (cityList == null) {
            cityList = new ArrayList<>();
            loaddata();
        }
        return cityList;
    }

    public List<CityData> getFavorCityList() {
        getCityList();
        List<CityData> favorCityList = new ArrayList<>();
        for (CityData cityData :
                cityList) {
            if (cityData.isFavoriteCity())
                favorCityList.add(cityData);
        }
        return favorCityList;
    }

    public String getDefaultCityName() {
        if (defaultCityName.equals(""))
            defaultCityName = context.getString(R.string.Kazan);
        return defaultCityName;
    }

    public void setDefaultCityName(String cityName) {
        defaultCityName = cityName;
        for (CityData cityData : cityList) {
            if (cityData.getName().equals(cityName))
                defaultKey = cityData.getKey();
        }
    }
}
