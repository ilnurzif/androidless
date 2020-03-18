package com.ilnur.appless;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class TheatherObservable {
    private List<TheatherObserver> theatherList = new ArrayList<>();

    public void registerObserver(TheatherObserver theatherObserver) {
        theatherList.add(theatherObserver);
    }

    public void deleteObserver(TheatherObserver theatherObserver) {
        theatherList.remove(theatherObserver);
    }

    public void notifyObserver(Context context, String msg) {
        for (TheatherObserver theatherObserver : theatherList) {
            theatherObserver.update(context, msg);
        }
    }
}
