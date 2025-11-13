package com.example.travelingapp.utils;

import android.text.TextUtils;
import android.util.Patterns;

public class InputValidator {

public static boolean validateRegisterInputs(
        String name,
        String lastName,
        String email,
        String password,
        String confirmPassword,
        String gender,
        String birthDate
) {
    return !TextUtils.isEmpty(name)
            && !TextUtils.isEmpty(lastName)
            && isValidEmail(email)
            && isValidPassword(password)
            && password.equals(confirmPassword)
            && !TextUtils.isEmpty(gender)
            && !TextUtils.isEmpty(birthDate);
}

public static boolean isValidEmail(String email) {
    return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
}

public static boolean isValidPassword(String password) {
    return !TextUtils.isEmpty(password) && password.length() >= 6;
}

}
