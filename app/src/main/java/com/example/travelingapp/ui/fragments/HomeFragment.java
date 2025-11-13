package com.example.travelingapp.ui.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.travelingapp.R;
import com.example.travelingapp.adapters.DestinationAdapter;
import com.example.travelingapp.ui.activities.DetailActivity;
import com.example.travelingapp.viewmodels.HomeViewModel;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import org.json.JSONStringer;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private ChipGroup chipGroupCatrgories;
    private Chip chipAll, chipBeach, chipAdventure;

    private RecyclerView recyclerView;
    private DestinationAdapter adapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewDestinations);

        chipAll = view.findViewById(R.id.chipAll);
        chipBeach = view.findViewById(R.id.chipBeaches);
        chipAdventure = view.findViewById(R.id.chipAdventure);

        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        chipAll.setOnClickListener(v -> homeViewModel.filterBy("all"));
        chipBeach.setOnClickListener(v -> homeViewModel.filterBy("beach"));
        chipAdventure.setOnClickListener(v -> homeViewModel.filterBy("adventure"));

        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        homeViewModel.getDestinations().observe(getViewLifecycleOwner(), destinations -> {
            adapter = new DestinationAdapter(requireContext(), destinations, destination -> {
                // Acción cuando hacen clic en el botón "Ver detalle"
                Toast.makeText(requireContext(), "Ver: " + destination.getName(), Toast.LENGTH_SHORT).show();

                // Luego puedes lanzar un Intent hacia DetailActivity aquí
                Intent intent = new Intent(requireContext(), DetailActivity.class);
                // agregando destination al intent
                intent.putExtra(DetailActivity.EXTRA_DESTINATION, destination);
                startActivity(intent);
            });

            recyclerView.setAdapter(adapter);
        });


        return view;
    }
}