package com.naura.less.appsettings;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.naura.less.MainActivity;
import com.naura.less.R;
import com.naura.less.observercode.EventsConst;
import com.naura.less.observercode.Observable;
import com.naura.less.observercode.Observer;

public class AppSettActivity extends BaseActivity implements Observer {
    private Observable observable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_settings);
        initVisual();
    }

    private void initVisual() {
        FloatingActionButton applySettfloatingActionButton = findViewById(R.id.applySettfloatingActionButton);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.themeList));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        final Spinner spinner = (Spinner) findViewById(R.id.theme_spinner);
        spinner.setAdapter(adapter);
        spinner.setPrompt("Title");

        if (isDarkTheme()) {
            spinner.setSelection(0);
        } else {
            spinner.setSelection(1);
        }

        applySettfloatingActionButton.setOnClickListener(new View.OnClickListener() {
                                                             @Override
                                                             public void onClick(View v) {
         switch (spinner.getSelectedItemPosition()) {
            case 0:
             setDarkTheme(true);
             observable.notify(EventsConst.themeReloadEvent, null);
             openMain();
             return;
           case 1:
             setDarkTheme(false);
             observable.notify(EventsConst.themeReloadEvent, null);
             openMain();
             return;
           }
          }                                                         }
        );

        observable = Observable.getInstance();
    }

    private void openMain() {
        Intent mainIntent = new Intent(AppSettActivity.this, MainActivity.class);
        this.startActivity(mainIntent);
    }

    @Override
    public <T> void update(String eventName, T val) {

    }
}
