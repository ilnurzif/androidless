package com.naura.less1pr;

import android.graphics.drawable.Drawable;

public class TheatherDay {
    private String temperature;
    private String weekday;
    private int theathericon;

    public String getTemperature() {
        return temperature;
    }

    public TheatherDay(String temperature, String weekday, int theathericon) {
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
