package com.naura.less.theatherdata;

import java.io.Serializable;

public class TheatherData implements Serializable {
    private String temperature;
    private String weekday;
    private int theathericon;

    public String getTemperature() {
        return temperature;
    }

    public TheatherData(String temperature, String weekday, int theathericon) {
        this.temperature = temperature;
        this.weekday = weekday;
        this.theathericon = theathericon;
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

    public void setTheathericon(int theathericon) {
        this.theathericon = theathericon;
    }
}