package com.ilnur.appless;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class StateObservable {
    private List<StateObserver> theatherList = new ArrayList<>();

    public void registerObserver(StateObserver theatherObserver) {
        theatherList.add(theatherObserver);
    }

    public void deleteObserver(StateObserver theatherObserver) {
        theatherList.remove(theatherObserver);
    }

    public void notifyObserver(Context context, String msg) {
        for (StateObserver theatherObserver : theatherList) {
            theatherObserver.update(context, msg);
        }
    }
}
