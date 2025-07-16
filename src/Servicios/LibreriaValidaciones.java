package Servicios;

import datos.Curso;
import datos.Validacion.ValidarCurso;

public class LibreriaValidaciones {

    public static boolean validarTextoNoVacio(String texto) {
        return texto != null && !texto.trim().isEmpty();
    }

    public static boolean validarNumeroNoNegativo(int numero) {
        return numero >= 0;
    }

    public static boolean validarLongitudMinima(String texto, int longitudMinima) {
        return texto != null && texto.trim().length() >= longitudMinima;
    }

    public static boolean validarLongitudMaxima(String texto, int longitudMaxima) {
        return texto != null && texto.trim().length() <= longitudMaxima;
    }

    public static boolean validarNota(int nota) {
        return nota >= 0 && nota <= 10;
    }
}
