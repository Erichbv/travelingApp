package com.example.travelingapp.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.travelingapp.R;
import com.example.travelingapp.utils.SharedPreferencesManager;
import com.example.travelingapp.viewmodels.LoginViewModel;
//import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private EditText etEmail, etPassword;
    private Button btnLogin;
    private TextView tvRegister;

    private LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        
        // incializar variables
        etEmail = findViewById(R.id.edtEmail);
        etPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvRegister = findViewById(R.id.txtRegister);


        // Inicializar ViewModel
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        
        // eventos de los botones
        btnLogin.setOnClickListener(this::onLoginClick);

        tvRegister.setOnClickListener(v -> {
            startActivity(new Intent(this, RegisterActivity.class));
        });

        // Observador de éxito de inicio de sesión
//        loginViewModel.getLoginSuccess().observe(this, user -> {
//            if (user != null) {
//                // guardar session en shared preferences
//                SharedPreferencesManager.getInstance(this).setLoginStatus(true);
//                SharedPreferencesManager.getInstance(this).setUserEmail(user.getEmail());
//
//                startActivity(new Intent(this, MainActivity.class));
//                finish();
//            }
//        });

        // Observador de error de inicio de sesión
//        loginViewModel.getLoginError().observe(this, error -> {
//            if (error != null) {
//                Toast.makeText(this, error, Toast.LENGTH_LONG).show();
//            }
//        });
    }
    
    public void onLoginClick(View view) {
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            // Mostrar error
            Toast.makeText(this, "Ingrese usuario o contraseña", Toast.LENGTH_SHORT).show();
            return;
        }

        loginViewModel.login(email, password);
    }
}