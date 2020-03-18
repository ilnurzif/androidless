package com.ilnur.appless;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {
    private CheckBox pressureCheckBox;
    private CheckBox windspeedCheckBox;
    private EditText selectCityEditText;
    TheatherViewSetting theatherViewSetting;
    private static final String theatherSettingState = "theatherSettingState";
    private StateObservable theatherObservable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.city_choice);
        initVisual();
        initObserver();
        theatherObservable.notifyObserver(this, "onCreate");
    }

    private void initVisual() {
        pressureCheckBox = findViewById(R.id.pressureCheckBox);
        windspeedCheckBox = findViewById(R.id.windspeedCheckBox);
        selectCityEditText = findViewById(R.id.selectCityEditText);
    }

    private void initObserver() {
        theatherObservable = new StateObservable();
        theatherObservable.registerObserver(new ShowMsgObserver());
        theatherObservable.registerObserver(new LogMsgObserver());
    }

    @Override
    protected void onStart() {
        super.onStart();
        theatherObservable.notifyObserver(this, "onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        theatherObservable.notifyObserver(this, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        theatherObservable.notifyObserver(this, "onDestroy");
    }

    @Override
    protected void onResume() {
        super.onResume();
        theatherObservable.notifyObserver(this, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        theatherObservable.notifyObserver(this, "onPause");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(theatherSettingState, saveState());
        super.onSaveInstanceState(outState);
        theatherObservable.notifyObserver(this, "onSaveInstanceState");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        restoreState((TheatherViewSetting) savedInstanceState.getSerializable(theatherSettingState));
        theatherObservable.notifyObserver(this, "onRestoreInstanceState");
    }

    private void restoreState(TheatherViewSetting theatherViewSetting) {
        this.theatherViewSetting = theatherViewSetting;
        pressureCheckBox.setChecked(theatherViewSetting.isPressureVisible());
        windspeedCheckBox.setChecked(theatherViewSetting.isWindspeedVisible());
        selectCityEditText.setText(theatherViewSetting.getSelCity());
    }

    private TheatherViewSetting saveState() {
        theatherViewSetting = TheatherViewSetting.getInstance();
        theatherViewSetting.setPressureVisible(pressureCheckBox.isChecked());
        theatherViewSetting.setWindspeedVisible(windspeedCheckBox.isChecked());
        theatherViewSetting.setSelCity(selectCityEditText.getText().toString());
        return theatherViewSetting;
    }
}

