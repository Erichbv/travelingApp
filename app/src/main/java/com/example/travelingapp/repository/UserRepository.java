package com.example.travelingapp.repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.travelgalleryapp.models.User;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class UserRepository {

    private final FirebaseFirestore firestore;
    private final MutableLiveData<Boolean> saveSuccess = new MutableLiveData<>();
    private final MutableLiveData<String> saveError = new MutableLiveData<>();

    public UserRepository() {
        firestore = FirebaseFirestore.getInstance();
    }

    public void saveUser(String userId, @NonNull User user) {
        Map<String, Object> userMap = new HashMap<>();
        userMap.put("name", user.getName());
        userMap.put("lastName", user.getLastName());
        userMap.put("email", user.getEmail());
        userMap.put("birthDate", user.getBrithday());
        userMap.put("gender", user.getGender());
        userMap.put("nationality", user.getNacionality());
        userMap.put("status", true);

        firestore.collection("users").document(userId)
                .set(userMap)
                .addOnSuccessListener(aVoid -> saveSuccess.setValue(true))
                .addOnFailureListener(e -> saveError.setValue(e.getMessage()));
    }

    public MutableLiveData<Boolean> getSaveSuccess() {
        return saveSuccess;
    }

    public MutableLiveData<String> getSaveError() {
        return saveError;
    }

}
