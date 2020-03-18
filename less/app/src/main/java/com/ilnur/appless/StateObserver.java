package com.ilnur.appless;

import android.content.Context;

interface StateObserver {
    public void update(Context context, String msg);
}
