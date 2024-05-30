package com.felinus.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidacionApp {

    // Expresión regular para validar un email
    private static final String EMAIL_PATTERN =
            "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

    // Compila la expresión regular en un patrón
    private static final Pattern patternEmail = Pattern.compile(EMAIL_PATTERN);

    // Método para validar el email
    public static boolean isValidEmail(String email) {
        if (email == null) {
            return false;
        }
        Matcher matcher = patternEmail.matcher(email);
        return matcher.matches();
    }

    // Expresión regular para validar un número de teléfono de 10 dígitos
    private static final String PHONE_PATTERN = "^\\d{10}$";

    // Compila la expresión regular en un patrón
    private static final Pattern patternTel = Pattern.compile(PHONE_PATTERN);

    // Método para validar el número de teléfono
    public static boolean isValidPhoneNumber(String phoneNumber) {
        if (phoneNumber == null) {
            return false;
        }
        Matcher matcher = patternTel.matcher(phoneNumber);
        return matcher.matches();
    }

    // Expresión regular para validar que el campo solo tenga texto
    private static final String TEXT_PATTERN = "^[A-Za-z\\s]+$";

    // Compila la expresión regular en un patrón
    private static final Pattern patternTexto = Pattern.compile(TEXT_PATTERN);

    // Método para validar que el campo solo tenga texto
    public static boolean isValidText(String text) {
        if (text == null) {
            return false;
        }
        Matcher matcher = patternTexto.matcher(text);
        return matcher.matches();
    }

    private static final String PASSWORD_PATTERN =
            "^(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$";


    // Compila la expresión regular en un patrón
    private static final Pattern patternPass = Pattern.compile(PASSWORD_PATTERN);

    // Método para validar la contraseña
    public static boolean isValidPassword(String password) {
        if (password == null) {
            return false;
        }
        Matcher matcher = patternPass.matcher(password);
        return matcher.matches();
    }

}
