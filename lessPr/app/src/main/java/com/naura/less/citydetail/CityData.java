package com.naura.less.citydetail;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;

import com.naura.less.theatherdata.TheatherData;

import java.util.List;


public class CityData {
    private String name;
    private List<TheatherData> theatherdays;
    private Bitmap verticalImage;
    private Bitmap horisontalImage;
    private Bitmap smallImage;
    private String infoUrl;

    public Bitmap getSmallImage() {
        return smallImage;
    }

    public CityData(String name, List<TheatherData> theatherdays,
                    Bitmap verticalImage,
                    Bitmap horisontalImage,
                    Bitmap smallImage,
                    String infoUrl) {

        this.name = name;
        this.verticalImage = verticalImage;
        this.horisontalImage = horisontalImage;
        this.theatherdays = theatherdays;
        this.smallImage = smallImage;
        this.infoUrl = infoUrl;
    }

    public int getTemperatureNow() {
        String st = theatherdays.get(0).getTemperature();
        return Integer.parseInt(st);
    }

    public int getAirhumidityNow() {
        String st = theatherdays.get(0).getAirhumidity();
        return Integer.parseInt(st);
    }

    public String getName() {
        return name;
    }

    public List<TheatherData> getTheatherdays() {
        return theatherdays;
    }

    public Bitmap getVerticalImage() {
        return verticalImage;
    }

    public Bitmap getHorisontalImage() {
        return horisontalImage;
    }

    public void openInfo(Context context) {
        Uri uri = Uri.parse(infoUrl);
        Intent browserintent = new Intent(Intent.ACTION_VIEW, uri);
        context.startActivity(browserintent);
    }
}
