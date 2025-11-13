package com.example.travelingapp.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.travelgalleryapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashActivity extends AppCompatActivity {

    private static  final int SPLASH_DELAY = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // evento deplay
        new Handler().postDelayed(() -> {
            FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
            if (currentUser != null) {
                goHome();
            } else {
                goLogin();
            }
        }, SPLASH_DELAY);
    }

    public void goHome() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void  goLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }


}