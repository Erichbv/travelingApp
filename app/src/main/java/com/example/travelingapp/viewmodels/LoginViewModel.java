package com.example.travelingapp.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.travelingapp.repository.AuthRepository;
//import com.google.firebase.auth.FirebaseUser;

public class LoginViewModel extends ViewModel {

    private final AuthRepository authRepository;


    public LoginViewModel() {
        authRepository = new AuthRepository();
    }

//    public MutableLiveData<FirebaseUser> getLoginSuccess() {
//        return authRepository.getLoginSuccess();
//    }

//    public MutableLiveData<String> getLoginError() {
//        return authRepository.getLoginError();
//    }

    public void login(String email, String password) {
        authRepository.login(email, password);
    }

//    public FirebaseUser getCurrentUser() {
//        return authRepository.getAuthInstance().getCurrentUser();
//    }
}
