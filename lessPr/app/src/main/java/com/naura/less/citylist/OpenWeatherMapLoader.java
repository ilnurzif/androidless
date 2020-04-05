package com.naura.less.citylist;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.naura.less.R;
import com.naura.less.observercode.EventsConst;
import com.naura.less.observercode.Observable;
import com.naura.less.theatherdata.TheatherData;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class OpenWeatherMapLoader extends CityLoader {
    private static final String LOG_TAG = "Debug";
    static private String defaultCityName = "";
    private static final Handler handler = new Handler();
    private static final String OPEN_WEATHER_API_KEY = "1d208cf3fc4085d8d8ba431d9d470fb3";
    private static final String OPEN_WEATHER_API_URL = "https://api.openweathermap.org/data/2.5/forecast?q=%s&units=metric&cnt=12&lang=ru";

    private static final String KEY = "x-api-key";
    private static OpenWeatherMapLoader openWeatherMapLoader;

    public void startLoad() {
        super.startLoad();
        updateWeatherData(context, getDefaultKey());
    }

    private OpenWeatherMapLoader(Context context) {
        super(context);
    }

    public static OpenWeatherMapLoader getInstance(Context context) {
        if (openWeatherMapLoader == null) {
            openWeatherMapLoader = new OpenWeatherMapLoader(context);
        }
        return openWeatherMapLoader;
    }

    private static JSONObject getJSONData(String city) {
        try {
            URL url = new URL(String.format(OPEN_WEATHER_API_URL, city));
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.addRequestProperty(KEY, OPEN_WEATHER_API_KEY);

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder rawData = new StringBuilder(1024);
            String tempVariable;

            while ((tempVariable = reader.readLine()) != null) {
                rawData.append(tempVariable).append("\n");
            }
            reader.close();
            JSONObject jsonObject = new JSONObject(rawData.toString());
            if (jsonObject.getInt("cod") != 200) {
                return null;
            } else {
                return jsonObject;
            }
        } catch (Exception exc) {
            exc.printStackTrace();
            return null;
        }
    }

    private void updateWeatherData(final Context context, final String city) {
        new Thread() {
            @Override
            public void run() {
                final JSONObject jsonObject = getJSONData(city);
                if (jsonObject == null) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Log.d(LOG_TAG, "not found");
                            Toast.makeText(context, "not found", Toast.LENGTH_LONG).show();
                        }
                    });
                } else {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            renderWeather(jsonObject);
                        }
                    });
                }
            }
        }.start();
    }

    private TheatherData jsonToTheatherData(JSONObject jsonObject, int index) {
        try {
            JSONObject list = jsonObject.getJSONArray("list").getJSONObject(index);
            JSONObject main = list.getJSONObject("main");
            String temper = String.format(Locale.getDefault(), "%.0f", main.getDouble("temp")) + "\u2103";
            String humidity = String.format(Locale.getDefault(), "%.0f", main.getDouble("humidity")) + "%";
            String pressure = String.format(Locale.getDefault(), "%.0f", main.getDouble("pressure")) + "hPa";

            JSONObject details = list.getJSONArray("weather").getJSONObject(0);
            String descriptionStr = details.getString("description");

            String dateS = list.getString("dt_txt");
            SimpleDateFormat dateFormat = new SimpleDateFormat();
            dateFormat.applyPattern("yyyy-MM-dd HH:mm:ss");
            Date tempDate = dateFormat.parse(dateS);
            SimpleDateFormat resDate = new SimpleDateFormat("E  dd.MM");

            return new TheatherData(temper, pressure, humidity, resDate.format(tempDate), descriptionStr, R.drawable.kweather);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void renderWeather(JSONObject jsonObject) {
        try {
            List<TheatherData> cityTheatherList = new ArrayList<>();

            cityTheatherList.add(jsonToTheatherData(jsonObject, 0));
            cityTheatherList.add(jsonToTheatherData(jsonObject, 6));
            cityTheatherList.add(jsonToTheatherData(jsonObject, 11));

            Observable observable = Observable.getInstance();
            observable.notify(EventsConst.cityLoadFinish, cityTheatherList);
        } catch (Exception exc) {
            exc.printStackTrace();
            Log.e(LOG_TAG, "One or more fields not found in the JSON data");
        }
    }

}
