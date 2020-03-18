package com.ilnur.appless;

import android.content.Context;
import android.widget.Toast;

public class ShowMsgObserver implements TheatherObserver {
    @Override
    public void update(Context context, String state) {
        Toast.makeText(context, state, Toast.LENGTH_SHORT).show();
    }

    ;
}
