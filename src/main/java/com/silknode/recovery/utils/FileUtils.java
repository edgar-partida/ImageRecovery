package com.silknode.recovery.utils;

import java.io.File;

public class FileUtils {

    public static String sanitizeFilename(String filename) {
        // Elimina rutas, deja solo el nombre
        String name = new File(filename).getName();
        // Reemplaza caracteres no permitidos por guión bajo
        return name.replaceAll("[^a-zA-Z0-9\\.\\-_]", "_");
    }
}
