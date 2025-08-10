package com.screenplay.project.utils;

import java.util.Random;

public class Utils {
    public static String generarEmailAleatorio() {
        String caracteres = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder correo = new StringBuilder("test");
        Random random = new Random();

        for (int i = 0; i < 8; i++) {
            correo.append(caracteres.charAt(random.nextInt(caracteres.length())));
        }
        correo.append("@gmail.com");
        return correo.toString();
    }
}
