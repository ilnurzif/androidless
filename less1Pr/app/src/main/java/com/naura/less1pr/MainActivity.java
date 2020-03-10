package com.naura.less1pr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    RecyclerView recyclerView;
    TheatherWeekAdapter adapter;
    List<TheatherDay> theatherDays=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle(R.string.theather);
        toolbar.setSubtitle(R.string.moscow);

        initVisual();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.weekdays);
        TheatherWeekAdapter adapter = new TheatherWeekAdapter(this, theatherDays);
        recyclerView.setAdapter(adapter);
  }

    private void initVisual() {
        theatherDays.add(new TheatherDay("19°",getString(R.string.Monday) ,R.drawable.kweather));
        theatherDays.add(new TheatherDay("10°",getString(R.string.Thursday),R.drawable.kweather));
        theatherDays.add(new TheatherDay("11°",getString(R.string.Tuesday),R.drawable.kweather));
    }
}
