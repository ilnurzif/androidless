package com.naura.less.citydetail;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;

import com.naura.less.theatherdata.TheatherData;

import java.util.List;

public class CityData {
    private String name;
    private List<TheatherData> theatherDays;
    private Bitmap verticalImage;
    private Bitmap horisontalImage;
    private Bitmap smallImage;
    private String infoUrl;
    private boolean favoriteCity = true;

    public boolean isFavoriteCity() {
        return favoriteCity;
    }

    public void setFavoriteCity(boolean favoriteCity) {
        this.favoriteCity = favoriteCity;
    }

    public CityData(String name, List<TheatherData> theatherDays,
                    Bitmap verticalImage,
                    Bitmap horisontalImage,
                    Bitmap smallImage,
                    String infoUrl) {

        this.name = name;
        this.verticalImage = verticalImage;
        this.horisontalImage = horisontalImage;
        this.theatherDays = theatherDays;
        this.smallImage = smallImage;
        this.infoUrl = infoUrl;
    }

    public Bitmap getSmallImage() {
        return smallImage;
    }

    public int getTemperatureNow() {
        String st = theatherDays.get(0).getTemperature();
        return Integer.parseInt(st);
    }

    public int getAirhumidityNow() {
        String st = theatherDays.get(0).getAirhumidity();
        return Integer.parseInt(st);
    }

    public String getName() {
        return name;
    }

    public List<TheatherData> getTheatherDays() {
        return theatherDays;
    }

    public Bitmap getVerticalImage() {
        return verticalImage;
    }

    public Bitmap getHorisontalImage() {
        return horisontalImage;
    }

    public void openInfo(Context context) {
        Uri uri = Uri.parse(infoUrl);
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, uri);
        context.startActivity(browserIntent);
    }
}
