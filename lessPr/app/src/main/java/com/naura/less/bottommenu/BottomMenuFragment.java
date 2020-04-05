package com.naura.less.bottommenu;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.naura.less.AboutActivity;
import com.naura.less.R;
import com.naura.less.appsettings.AppSettActivity;
import com.naura.less.observercode.Observer;

public class BottomMenuFragment extends Fragment implements Observer {

    private void openAbout() {
        Intent aboutIntent = new Intent(getActivity(), AboutActivity.class);
        this.startActivity(aboutIntent);
    }

    private void openSettings() {
        Intent setIntent = new Intent(getActivity(), AppSettActivity.class);
        this.startActivity(setIntent);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.bottom_nav, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
    }

    private void initViews(View view) {
        BottomNavigationView botView = view.findViewById(R.id.nav_view);
        botView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.settings:
                        openSettings();
                        return true;
                    case R.id.about:
                        openAbout();
                        return true;
                }
                return false;
            }

        });
    }

    @Override
    public <T> void update(String eventName, T val) {

    }
}
