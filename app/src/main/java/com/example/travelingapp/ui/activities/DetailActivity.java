package com.example.travelingapp.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.widget.Button;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.travelgalleryapp.R;
import com.example.travelgalleryapp.models.Destination;

import com.example.travelgalleryapp.ui.fragments.WebviewFragment;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_DESTINATION = "destination";

    private ImageView ivImage;
    private TextView tvName, tvLocation, tvDescription;
    private Button btnMoreDetail;

    private Destination destination;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ivImage = findViewById(R.id.imageViewDetail);
        tvName = findViewById(R.id.textViewTitle);
        tvLocation = findViewById(R.id.textViewLocation);
        tvDescription = findViewById(R.id.textViewCategory);
        btnMoreDetail = findViewById(R.id.btnMoreDetails);

        // Obtener el destino de los extras del Intent

        if (getIntent() != null && getIntent().hasExtra(EXTRA_DESTINATION)) {
            destination = (Destination) getIntent().getSerializableExtra(EXTRA_DESTINATION);
            populateData(destination);
        }

        btnMoreDetail.setOnClickListener(v -> {
            if (destination != null && destination.getWebUrl() != null) {
                openWebView(destination.getWebUrl());
            }
        });

    }

    private void populateData(Destination destination) {
        tvName.setText(destination.getName());
        tvLocation.setText(destination.getLocation());
        tvDescription.setText(destination.getDescription());

        Glide.with(this)
                .load(destination.getImageUrl())
                .placeholder(R.drawable.ic_placeholder)
                .into(ivImage);
    }

    private void openWebView(String url) {
//        Fragment webFragment = new WebviewFragment();
//        WebviewFragment fragment = webFragment.newInstance(destination.getWebUrl());
//
//        getSupportFragmentManager()
//                .beginTransaction()
//                .replace(R.id.fragment_container, fragment)
//                .addToBackStack(null)
//                .commit();



    }
}