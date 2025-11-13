package com.example.travelingapp.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.travelgalleryapp.R;
import com.example.travelgalleryapp.models.User;
import com.example.travelgalleryapp.utils.InputValidator;
import com.example.travelgalleryapp.viewmodels.RegisterViewModel;

import java.util.Calendar;

public class RegisterActivity extends AppCompatActivity {

    private EditText edtName, edtLastname, edtEmail, edtPassword, edtConfirmPassword;
    private RadioGroup rgbGender;
    private Spinner spnNacionality;
    private TextView tvBirthDate;
    private Button btnRegister, btnCancell;

    private RegisterViewModel registerViewModel;
    private String selectedBirthDate = "";
    private String selectedGender = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtName = findViewById(R.id.edtName);
        edtLastname = findViewById(R.id.edtLastname);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        edtConfirmPassword = findViewById(R.id.edtConfirmPassword);
        rgbGender = findViewById(R.id.rgbGender);
        spnNacionality = findViewById(R.id.spnNacionality);
        tvBirthDate = findViewById(R.id.tvBirthDate);
        btnRegister = findViewById(R.id.btnRegister);
        btnCancell = findViewById(R.id.btnCancell);
        
        setupNationalitySpinner();

        // Inicializar ViewModel
        registerViewModel = new ViewModelProvider(this).get(RegisterViewModel.class);

        // eventos de los botones
        tvBirthDate.setOnClickListener(v -> showDatePicker());

        rgbGender.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.rbMale) selectedGender = "Masculino";
            else if (checkedId == R.id.rbFemale) selectedGender = "Femenino";
            else selectedGender = "Otro";
        });

        btnRegister.setOnClickListener(v -> registerUser());
        
        // observadores de los resultados del registro
        registerViewModel.getRegisterError().observe(this, error -> {
            if (error != null) showToast(error);
        });

        registerViewModel.getUserSaveSuccess().observe(this, success -> {
            if (success != null && success) {
                showToast("¡Registro exitoso!");
                finish();
            }
        });

        registerViewModel.getUserSaveError().observe(this, error -> {
            if (error != null) showToast("Error al guardar usuario: " + error);
        });

    }

    private void showDatePicker() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog picker = new DatePickerDialog(this,
                (view, year1, month1, dayOfMonth) -> {
                    selectedBirthDate = dayOfMonth + "/" + (month1 + 1) + "/" + year1;
                    tvBirthDate.setText(selectedBirthDate);
                }, year, month, day);
        picker.show();
    }

    private void setupNationalitySpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.nationalities_array,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnNacionality.setAdapter(adapter);
    }

    private void showToast(String message) {
        Toast.makeText(this, "", Toast.LENGTH_LONG).show();
    }



    private void registerUser() {
        String name = edtName.getText().toString().trim();
        String lastName = edtLastname.getText().toString().trim();
        String email = edtEmail.getText().toString().trim();
        String password = edtPassword.getText().toString();
        String confirmPassword = edtConfirmPassword.getText().toString();
        String nationality = spnNacionality.getSelectedItem().toString();

        if (!InputValidator.validateRegisterInputs(name, lastName, email, password, confirmPassword, selectedGender, selectedBirthDate)) {
            showToast("Por favor, complete todos los campos correctamente.");
            return;
        }

        User user = new User();
        user.setName(name);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setGender(selectedGender);
        user.setBrithday(selectedBirthDate);
        user.setNacionality(nationality);

        registerViewModel.registerUser(user, password);
    }
}