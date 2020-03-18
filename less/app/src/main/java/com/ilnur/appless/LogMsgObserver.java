package com.ilnur.appless;

import android.content.Context;
import android.util.Log;

public class LogMsgObserver implements StateObserver {
    private static final String Tag = "Debug";

    @Override
    public void update(Context context, String state) {
        Log.d(Tag, state);
    }
}
