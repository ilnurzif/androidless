package com.naura.less.citylist;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.naura.less.R;
import com.naura.less.citydetail.CityData;
import com.naura.less.theatherdata.TheatherData;

import java.util.ArrayList;
import java.util.List;

public class CityLoader {
    static private List<CityData> cityList;
    static private String defaultCityName = "";

    private static void loaddata(Context context) {
        context.getString(R.string.Moscow);
        List<TheatherData> moscowTheatherList = new ArrayList<>();

        moscowTheatherList.add(new TheatherData("10", "748", "48", context.getString(R.string.Monday), R.drawable.kweather));
        moscowTheatherList.add(new TheatherData("13", "766", "33", context.getString(R.string.Thursday), R.drawable.kweather));
        moscowTheatherList.add(new TheatherData("17", "700", "56", context.getString(R.string.Saturday), R.drawable.kweather));

        cityList.add(new CityData(context.getString(R.string.Moscow),
                moscowTheatherList,
                ResToBitmap(context, R.drawable.moscowsity),
                ResToBitmap(context, R.drawable.moscowhorizont),
                ResToBitmap(context, R.drawable.kazan_small),
                "https://ru.wikipedia.org/wiki/%D0%9C%D0%BE%D1%81%D0%BA%D0%B2%D0%B0"));

        List<TheatherData> kazanTheatherList = new ArrayList<>();
        kazanTheatherList.add(new TheatherData("17", "700", "33", context.getString(R.string.Monday), R.drawable.kweather));
        kazanTheatherList.add(new TheatherData("16", "666", "66", context.getString(R.string.Thursday), R.drawable.kweather));
        kazanTheatherList.add(new TheatherData("17", "744", "38", context.getString(R.string.Saturday), R.drawable.kweather));

        cityList.add(new CityData(context.getString(R.string.Kazan),
                kazanTheatherList,
                ResToBitmap(context, R.drawable.kazanvertical),
                ResToBitmap(context, R.drawable.kazanhorizontal),
                ResToBitmap(context, R.drawable.kazan_small),
                "https://ru.wikipedia.org/wiki/%D0%9A%D0%B0%D0%B7%D0%B0%D0%BD%D1%8C"));
    }

    private static Bitmap ResToBitmap(Context context, int resid) {
        return BitmapFactory.decodeResource(context.getResources(), resid);
    }

    private static CityData findCity(String cityname) {
        for (CityData cd : cityList) {
            if (cd.getName().equals(cityname))
                return cd;
        }
        return null;
    }

    public static void SetlikeCity(String cityName) {
        for (CityData cityData : cityList) {
            if (cityData.getName().equals(cityName))
                cityData.setFavoriteCity(!cityData.isFavoriteCity());
        }
    }

    public static CityData getCity(Context context, String cityname) {
        if (cityList == null) {
            cityList = new ArrayList<>();
            loaddata(context);
        }
        return findCity(cityname);
    }

    public static List<TheatherData> getTheatherData(Context context, String cityname) {
        CityData cityData = getCity(context, cityname);
        return (cityData == null) ? null : cityData.getTheatherDays();
    }

    public static List<CityData> getCityList(Context context) {
        if (cityList == null) {
            cityList = new ArrayList<>();
            loaddata(context);
        }
        return cityList;
    }

    public static List<CityData> getFavorCityList(Context context) {
        getCityList(context);
        List<CityData> favorCityList = new ArrayList<>();
        for (CityData cityData :
                cityList) {
            if (cityData.isFavoriteCity())
                favorCityList.add(cityData);
        }
        return favorCityList;
    }

    public static String getDefaultCityName(Context context) {
        if (defaultCityName.equals(""))
            defaultCityName = context.getString(R.string.Moscow);
        return defaultCityName;
    }

    public static void setDefaultCityName(String cityName) {
        defaultCityName = cityName;
    }
}
