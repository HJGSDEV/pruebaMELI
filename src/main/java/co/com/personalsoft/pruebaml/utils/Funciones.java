package co.com.personalsoft.pruebaml.utils;

import java.util.function.Function;

public class Funciones {

    static Function<String, Boolean> datoPermitido = (dato) -> {
        Boolean validacion = false;
        switch (dato) {
            case "A":
                validacion = true;
                break;
            case "T":
                validacion = true;
                break;
            case "C":
                validacion = true;
                break;
            case "G":
                validacion = true;
                break;
        }
        return validacion;
    };
    
    public static Function<String[], String[][]> generarMatriz = (dnaArray) -> {
        String[][] matrizDna = new String[dnaArray.length][];
        for (int fila = 0; fila < dnaArray.length; fila++) {
            String[] subDatos = dnaArray[fila].split("");
            matrizDna[fila] = new String[subDatos.length];
            for (int columna = 0; columna < subDatos.length; columna++) {
                if (datoPermitido.apply(subDatos[columna])) {
                    matrizDna[fila][columna] = subDatos[columna];
                } else {
                    return null;
                }
            }
        }
        return matrizDna;
    };
}

