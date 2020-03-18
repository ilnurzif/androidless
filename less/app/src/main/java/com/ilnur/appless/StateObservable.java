package com.ilnur.appless;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class StateObservable {
    private List<SaveObserver> theatherList = new ArrayList<>();

    public void registerObserver(SaveObserver theatherObserver) {
        theatherList.add(theatherObserver);
    }

    public void deleteObserver(SaveObserver theatherObserver) {
        theatherList.remove(theatherObserver);
    }

    public void notifyObserver(Context context, String msg) {
        for (SaveObserver theatherObserver : theatherList) {
            theatherObserver.update(context, msg);
        }
    }
}
