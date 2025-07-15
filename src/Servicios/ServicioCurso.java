package Servicios;

import datos.Curso;
import datos.Excepcion.CursoException;

public class ServicioCurso {

    public Curso crearCurso(String nombre, String cupoStr, String precioStr, String notaStr, String profesorDni) {
        if (nombre.isEmpty() || cupoStr.isEmpty() || precioStr.isEmpty() || notaStr.isEmpty() || profesorDni.isEmpty()) {
            throw new CursoException("Todos los campos deben estar completos.");
        }

        try {
            int cupo = Integer.parseInt(cupoStr);
            int precio = Integer.parseInt(precioStr);
            int notaAprobacion = Integer.parseInt(notaStr);
            return new Curso(nombre.toUpperCase(), cupo, precio, notaAprobacion, profesorDni.toUpperCase());
        } catch (NumberFormatException e) {
            throw new CursoException("Cupo, precio y nota deben ser números válidos.");
        }
    }
}
