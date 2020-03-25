package com.naura.less.theatherdata;

import java.io.Serializable;

public class TheatherData implements Serializable {
    private String temperature;
    private String weekday;
    private String pressure;
    private String airhumidity;
    private int theathericon;

    public String getTemperature() {
        return temperature;
    }

    public String getAirhumidity() {
        return airhumidity;
    }

    public TheatherData(String temperature, String pressure, String airhumidity, String weekday, int theathericon) {
        this.temperature = temperature;
        this.weekday = weekday;
        this.theathericon = theathericon;
        this.pressure = pressure;
        this.airhumidity = airhumidity;
    }

    public String getWeekday() {
        return weekday;
    }

    public int getTheathericon() {
        return theathericon;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public void setWeekday(String weekday) {
        this.weekday = weekday;
    }

    public void setTheatherIcon(int theatherIcon) {
        this.theathericon = theatherIcon;
    }
}