package Servicios;

import datos.Curso;
import datos.Excepcion.ValidacionException;
import datos.Validacion.ValidarCurso;

public class LibreriaValidaciones {

    public static void validarTextoNoVacio(String texto) throws ValidacionException {
        if (texto == null || texto.trim().isEmpty()) {
            throw new ValidacionException("El texto no puede estar vacío");
        }
    }

    public static void validarNumeroNoNegativo(int numero) throws ValidacionException {
        if (numero < 0) {
            throw new ValidacionException("El número no puede ser negativo");
        }
    }

    public static void validarLongitudMinima(String texto, int longitudMinima) throws ValidacionException {
        if (texto == null || texto.trim().length() < longitudMinima) {
            throw new ValidacionException("La longitud mínima es " + longitudMinima);
        }
    }

    public static void validarLongitudMaxima(String texto, int longitudMaxima) throws ValidacionException {
        if (texto == null || texto.trim().length() > longitudMaxima) {
            throw new ValidacionException("La longitud maxima es " + longitudMaxima);
        }
    }

    public static void validarNota(int nota) throws ValidacionException {
        if (nota < 0 || nota > 10) {
            throw new ValidacionException("La nota debe estar entre 0 y 10");
        }
    }
}
