package datos.Validacion;

import datos.Curso;
import datos.Excepcion.CursoException;

public class ValidarCurso {

    public static void validar(Curso curso) throws CursoException {
        if (curso == null) {
            throw new CursoException("El curso no puede ser nulo.");
        }

        if (curso.getNombre() == null || curso.getNombre().isBlank()) {
            throw new CursoException("El nombre del curso es obligatorio.");
        }

        if (curso.getCupoMaximo() <= 0) {
            throw new CursoException("El cupo debe ser mayor que 0.");
        }

        if (curso.getPrecio() < 0) {
            throw new CursoException("El precio no puede ser negativo.");
        }

        if (curso.getNotaAprobacion() < 1 || curso.getNotaAprobacion() > 10) {
            throw new CursoException("La nota de aprobación debe estar entre 1 y 10.");
        }
    }
}
