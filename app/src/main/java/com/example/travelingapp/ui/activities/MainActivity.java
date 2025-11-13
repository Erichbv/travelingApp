package com.example.travelingapp.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.example.travelingapp.R;
import com.example.travelingapp.ui.fragments.HomeFragment;
import com.example.travelingapp.ui.fragments.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottonNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottonNavigation = findViewById(R.id.bottom_navigation);

        // cargar el frament por defecto
        loadFragment(new HomeFragment());

        bottonNavigation.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;

            int id = item.getItemId();

            if(id == R.id.nav_profile) {
                selectedFragment = new ProfileFragment();
            } else if (id == R.id.nav_home) {
                selectedFragment = new HomeFragment();
            } else {
                selectedFragment = new HomeFragment();
            }

            return loadFragment(selectedFragment);
        });

    }

    private boolean loadFragment(Fragment fragment) {
        if(fragment != null) {
            getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
            return true;
        }

        return false;
    }
}