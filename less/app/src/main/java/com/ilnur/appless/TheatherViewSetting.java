package com.ilnur.appless;

import java.io.Serializable;

public class TheatherViewSetting implements Serializable {
    private static TheatherViewSetting theatherViewSetting;
    private boolean pressureVisible;
    private boolean windspeedVisible;
    private String selCity;

    public String getSelCity() {
        return selCity;
    }

    public void setSelCity(String selCity) {
        this.selCity = selCity;
    }

    private TheatherViewSetting() {
    }

    public static TheatherViewSetting getInstance() {
        if (theatherViewSetting == null) {
            theatherViewSetting = new TheatherViewSetting();
        }
        return theatherViewSetting;
    }

    public boolean isPressureVisible() {
        return pressureVisible;
    }

    public void setPressureVisible(boolean pressureVisible) {
        this.pressureVisible = pressureVisible;
    }

    public boolean isWindspeedVisible() {
        return windspeedVisible;
    }

    public void setWindspeedVisible(boolean windspeedVisible) {
        this.windspeedVisible = windspeedVisible;
    }
}
