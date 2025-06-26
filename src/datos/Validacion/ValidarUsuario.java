package datos.Validacion;

import datos.Curso;
import datos.Excepcion.CursoException;
import datos.Excepcion.UsuarioExcepction;
import datos.Usuario;

public class ValidarUsuario {

    public static void validar(Usuario usuario) throws UsuarioExcepction {
        if (usuario == null) {
            throw new CursoException("El curso no puede ser nulo.");
        }

        if (usuario.getNombre() == null || usuario.getNombre().isBlank()) {
            throw new CursoException("El nombre del curso es obligatorio.");
        }

        if (usuario.getEmail() == null || usuario.getEmail().isBlank() ) {
            throw new CursoException("El email debe no debe ser en blanco");
        }

        if (usuario.getId() == null || usuario.getEmail().isBlank()) {
            throw new CursoException("El id debe no debe ser en blanco.");
        }

        if (usuario.getRol() == null) {
            throw new CursoException("El rol debe no debe ser en blanco..");
        }
    }
}
