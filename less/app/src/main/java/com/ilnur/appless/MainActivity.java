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
    private StateObservable stateObservable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.city_choice);
        initVisual();
        initObserver();
        stateObservable.notifyObserver(this, "onCreate");
    }

    private void initVisual() {
        pressureCheckBox = findViewById(R.id.pressureCheckBox);
        windspeedCheckBox = findViewById(R.id.windspeedCheckBox);
        selectCityEditText = findViewById(R.id.selectCityEditText);
    }

    private void initObserver() {
        stateObservable = new StateObservable();
        stateObservable.registerObserver(new ShowMsgObserver());
        stateObservable.registerObserver(new LogMsgObserver());
    }

    @Override
    protected void onStart() {
        super.onStart();
        stateObservable.notifyObserver(this, "onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        stateObservable.notifyObserver(this, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stateObservable.notifyObserver(this, "onDestroy");
    }

    @Override
    protected void onResume() {
        super.onResume();
        stateObservable.notifyObserver(this, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        stateObservable.notifyObserver(this, "onPause");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(theatherSettingState, saveState());
        super.onSaveInstanceState(outState);
        stateObservable.notifyObserver(this, "onSaveInstanceState");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        restoreState((TheatherViewSetting) savedInstanceState.getSerializable(theatherSettingState));
        stateObservable.notifyObserver(this, "onRestoreInstanceState");
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

