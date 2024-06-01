package com.felinus.validators;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.*;

public class ValidacionAppTest {

    //PRUEBAS PARA VERIFICAR EL EMAIL
//    @Test
//    public void elEmailTieneQueTenerArroba() {
//        boolean resultado = ValidacionApp.isValidEmail("asasdfasdfas");
//        assertFalse(resultado);
//    }
//
//    @Test
//    public void emailValidoDebeSerAceptado() {
//        boolean resultado = ValidacionApp.isValidEmail("ejemplo@dominio.com");
//        assertTrue(resultado);
//    }
//
//    @Test
//    public void emailConCaracteresEspecialesDebeSerAceptado() {
//        boolean resultado = ValidacionApp.isValidEmail("nombre.apellido+alias@dominio.com");
//        assertTrue(resultado);
//    }
//
//    @Test
//    public void emailSinNombreDeDominioNoDebeSerAceptado() {
//        boolean resultado = ValidacionApp.isValidEmail("usuario@");
//        assertFalse(resultado);
//    }

    //PRUEBAS PARA VERIFICAR EL PHONE NUMBER
//    @Test
//    public void numeroDeTelefonoDebeTener10Digitos() {
//        boolean resultado = ValidacionApp.isValidPhoneNumber("9515212312");
//        assertTrue(resultado);
//    }
//
//    @Test
//    public void numeroDeTelefonoConMenosDe10DigitosNoDebeSerAceptado() {
//        boolean resultado = ValidacionApp.isValidPhoneNumber("12345");
//        assertFalse(resultado);
//    }
//
//    @Test
//    public void numeroDeTelefonoNuloNoDebeSerAceptado() {
//        boolean resultado = ValidacionApp.isValidPhoneNumber(null);
//        assertFalse(resultado);
//    }
//
//    //PARA VERIFICAR CAMPOS DE TEXTO
//    @Test
//    public void textoConSoloLetrasYEspaciosDebeSerAceptado() {
//        boolean resultado = ValidacionApp.isValidText("Solo Texto");
//        assertTrue(resultado);
//    }
//
//    @Test
//    public void textoConNumerosNoDebeSerAceptado() {
//        boolean resultado = ValidacionApp.isValidText("Texto123");
//        assertFalse(resultado);
//    }
//
//    @Test
//    public void textoConCaracteresEspecialesNoDebeSerAceptado() {
//        boolean resultado = ValidacionApp.isValidText("Texto@#");
//        assertFalse(resultado);
//    }
//
//    @Test
//    public void textoNuloNoDebeSerAceptado() {
//        boolean resultado = ValidacionApp.isValidText(null);
//        assertFalse(resultado);
//    }
//
//    @Test
//    public void textoVacioNoDebeSerAceptado() {
//        boolean resultado = ValidacionApp.isValidText("");
//        assertFalse(resultado);
//    }

//
    @Test
    public void contrasenaValidaDebeSerAceptada() {
        boolean resultado = ValidacionApp.isValidPassword("Password1!");
        assertTrue(resultado);
    }

    @Test
    public void contrasenaSinMayusculasNoDebeSerAceptada() {
        boolean resultado = ValidacionApp.isValidPassword("password1!");
        assertFalse(resultado);
    }

    @Test
    public void contrasenaSinCaracterEspecialNoDebeSerAceptada() {
        boolean resultado = ValidacionApp.isValidPassword("Password1");
        assertFalse(resultado);
    }

    @Test
    public void contrasenaSinNumeroNoDebeSerAceptada() {
        boolean resultado = ValidacionApp.isValidPassword("Password!");
        assertFalse(resultado);
    }

    @Test
    public void contrasenaConMenosDe8CaracteresNoDebeSerAceptada() {
        boolean resultado = ValidacionApp.isValidPassword("Pass1!");
        assertFalse(resultado);
    }

    @Test
    public void contrasenaNulaNoDebeSerAceptada() {
        boolean resultado = ValidacionApp.isValidPassword(null);
        assertFalse(resultado);
    }


}
