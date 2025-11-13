package com.example.travelingapp.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.travelgalleryapp.models.Destination;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<List<Destination>> destinationsLiveData = new MutableLiveData<>();
    private final List<Destination> fullList = new ArrayList<>();


    public HomeViewModel() {
        // Inicializar con datos mock
        loadMockDestinations();
    }

    public LiveData<List<Destination>> getDestinations() {
        return destinationsLiveData;
    }

    private void loadMockDestinations() {
        fullList.clear();

        fullList.add(new Destination("1", "Machu Picchu", "Antigua ciudad inca en Cusco", "https://upload.wikimedia.org/wikipedia/commons/e/eb/Machu_Picchu%2C_Peru.jpg", "Perú", "all", "https://es.wikipedia.org/wiki/Machu_Picchu"));
        fullList.add(new Destination("2", "Playa Máncora", "Hermosa playa en el norte del Perú", "https://upload.wikimedia.org/wikipedia/commons/6/66/Mancora_Peru.jpg", "Perú", "beach", "https://es.wikipedia.org/wiki/M%C3%A1ncora"));
        fullList.add(new Destination("3", "Lago Titicaca", "El lago navegable más alto del mundo", "https://upload.wikimedia.org/wikipedia/commons/b/b0/Lake_Titicaca_from_Space.jpg", "Perú", "adventure", "https://es.wikipedia.org/wiki/Lago_Titicaca"));
        fullList.add(new Destination("4", "Cañón del Colca", "Uno de los cañones más profundos del mundo", "https://upload.wikimedia.org/wikipedia/commons/5/5e/Colca_Canyon.jpg", "Perú", "adventure", "https://es.wikipedia.org/wiki/Ca%C3%B1%C3%B3n_del_Colca"));

        destinationsLiveData.setValue(new ArrayList<>(fullList));
    }

    public void filterBy(String category) {
        if (category.equals("all")) {
            destinationsLiveData.setValue(new ArrayList<>(fullList));
        } else {
            List<Destination> filtered = new ArrayList<>();
            for (Destination d : fullList) {
                if (d.getCategory().equalsIgnoreCase(category)) {
                    filtered.add(d);
                }
            }
            destinationsLiveData.setValue(filtered);
        }
    }

}
