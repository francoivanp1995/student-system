package Servicios.Validacion;

import datos.Excepcion.ValidacionException;
import datos.RolUsuario;
import datos.Usuario;

public class ValidarUsuario {

    public static void validar(Usuario usuario) throws ValidacionException
    {
        //Agregar mas validaciones
        //Hacer una interfaz. Chequear con validar Curso.
        if (usuario == null) {
            throw new ValidacionException("El usuario no puede ser nulo.");
        }

        if (usuario.getNombre() == null || usuario.getNombre().isBlank()) {
            throw new ValidacionException("El nombre del usuario es obligatorio.");
        }

        if (usuario.getEmail() == null || usuario.getEmail().isBlank() ) {
            throw new ValidacionException("El email debe no debe ser en blanco");
        }

        if (usuario.getId() == null || usuario.getId().isBlank()) {
            throw new ValidacionException("El id debe no debe ser en blanco.");
        }

        if (usuario.getRol() == null ||
                (usuario.getRol() != RolUsuario.ADMINISTRADOR &&
                        usuario.getRol() != RolUsuario.ALUMNO &&
                        usuario.getRol() != RolUsuario.PROFESOR)) {
            throw new ValidacionException("El rol del usuario es inválido.");
        }
    }
}
