package com.example.travelingapp.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.travelingapp.models.User;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.firestore.FirebaseFirestore;

public class AuthRepository {

//    private static FirebaseAuth firebaseAuth;
//
//    private final MutableLiveData<FirebaseUser> loginSuccess = new MutableLiveData<>();
//    private final MutableLiveData<String> loginError = new MutableLiveData<>();
//    private final FirebaseFirestore firestore;
//    private final MutableLiveData<FirebaseUser> registerSuccess = new MutableLiveData<>();
    private final MutableLiveData<String> registerError = new MutableLiveData<>();

    private UserRepository userRepository;

    public AuthRepository() {
//        firebaseAuth = FirebaseAuth.getInstance();
//        firestore = FirebaseFirestore.getInstance();
        userRepository = new UserRepository();
    }

//    public FirebaseAuth getAuthInstance() {
//        if(firebaseAuth == null) {
//            firebaseAuth = FirebaseAuth.getInstance();
//        }
//        return firebaseAuth;
//    }

    public void login(String email, String password) {
//        firebaseAuth.signInWithEmailAndPassword(email, password)
//                .addOnCompleteListener(task -> {
//                   if(task.isSuccessful() && task.getResult() != null) {
//                       loginSuccess.setValue(firebaseAuth.getCurrentUser());
//                   } else {
//                       loginError.setValue( task.getException() != null ?
//                               task.getException().getMessage() : "Hubo un error al logearte");
//                   }
//                });
    }


//    public MutableLiveData<FirebaseUser> getLoginSuccess() {
//        return loginSuccess;
//    }

//    public MutableLiveData<String> getLoginError() {
//        return loginError;
//    }

//    public void register(User user, String password) {
//        firebaseAuth.createUserWithEmailAndPassword(user.getEmail(), password)
//                .addOnCompleteListener(task -> {
//                    if (task.isSuccessful() && task.getResult() != null) {
//                        FirebaseUser firebaseUser = task.getResult().getUser();
//                        if (firebaseUser != null) {
//                            registerSuccess.setValue(firebaseUser);
//                        }
//                    } else {
//                        registerError.setValue(task.getException() != null
//                                ? task.getException().getMessage()
//                                : "Error al registrar al usuario.");
//                    }
//                });
//    }

//    public MutableLiveData<FirebaseUser> getRegisterSuccess() {
//        return registerSuccess;
//    }

    public MutableLiveData<String> getRegisterError() {
        return registerError;
    }
}
