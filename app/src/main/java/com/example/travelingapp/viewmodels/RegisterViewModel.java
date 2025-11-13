package com.example.travelingapp.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.travelgalleryapp.models.User;
import com.example.travelgalleryapp.repository.AuthRepository;
import com.example.travelgalleryapp.repository.UserRepository;
import com.google.firebase.auth.FirebaseUser;

public class RegisterViewModel extends ViewModel {

    private final AuthRepository authRepository;
    private final UserRepository userRepository;

    private final MutableLiveData<Boolean> userSaveSuccess = new MutableLiveData<>();
    private final MutableLiveData<String> userSaveError = new MutableLiveData<>();

    public RegisterViewModel() {
        authRepository = new AuthRepository();
        userRepository = new UserRepository();

        // Observamos el éxito del registro para guardar en Firestore
        authRepository.getRegisterSuccess().observeForever(user -> {
            if (user != null) {
                saveUserData(user.getUid(), tempUser);
            }
        });
    }

    private User tempUser;

    public void registerUser(User user, String password) {
        tempUser = user; // guardamos temporalmente los datos para usar luego del registro
        authRepository.register(user, password);
    }

    private void saveUserData(String userId, User user) {
        userRepository.saveUser(userId, user);
        userRepository.getSaveSuccess().observeForever(success -> {
            if (success != null && success) {
                userSaveSuccess.setValue(true);
            }
        });
        userRepository.getSaveError().observeForever(error -> {
            if (error != null) {
                userSaveError.setValue(error);
            }
        });
    }

    public LiveData<FirebaseUser> getRegisterSuccess() {
        return authRepository.getRegisterSuccess();
    }

    public LiveData<String> getRegisterError() {
        return authRepository.getRegisterError();
    }

    public LiveData<Boolean> getUserSaveSuccess() {
        return userSaveSuccess;
    }

    public LiveData<String> getUserSaveError() {
        return userSaveError;
    }
}
