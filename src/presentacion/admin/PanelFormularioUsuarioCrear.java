package presentacion.admin;

import datos.*;
import datos.Excepcion.UsuarioExcepction;
import presentacion.PanelCampoFormulario;
import presentacion.abstracto.PanelFormularioBase;

import java.awt.*;

public class PanelFormularioUsuarioCrear extends PanelFormularioBase<Usuario> {

    private final PanelCampoFormulario campoDNI;
    private final PanelCampoFormulario campoNombre;
    private final PanelCampoFormulario campoApellido;
    private final PanelCampoFormulario campoNombreUsuario;
    private final PanelCampoFormulario campoTipo;
    private final PanelCampoFormulario campoLimiteCursos;
    private final PanelCampoFormulario campoEmail;
    private final PanelCampoFormulario campoContrasenia;

    public PanelFormularioUsuarioCrear() {
        this(null); // Reutiliza el constructor que acepta Curso
    }


    public PanelFormularioUsuarioCrear(Usuario usuario) {
        setLayout(new GridLayout(8, 1, 10, 10));

        campoDNI = new PanelCampoFormulario("DNI:");
        campoNombre = new PanelCampoFormulario("Nombre:");
        campoApellido = new PanelCampoFormulario("Apellido:");
        campoNombreUsuario = new PanelCampoFormulario("Nombre de usuario:");
        campoTipo = new PanelCampoFormulario("Tipo:");
        campoLimiteCursos = new PanelCampoFormulario("Limite Cursos:");
        campoEmail = new PanelCampoFormulario("Email:");
        campoContrasenia = new PanelCampoFormulario("Contrasenia:");


        add(campoDNI);
        add(campoNombre);
        add(campoApellido);
        add(campoNombreUsuario);
        add(campoTipo);
        add(campoLimiteCursos);
        add(campoEmail);
        add(campoContrasenia);


    }

    public Usuario construirUIForm() {

        String dni = campoDNI.getTexto().trim();
        String nombre = campoNombre.getTexto().trim();
        String apellido = campoApellido.getTexto().trim();
        String nombreUsuario = campoNombreUsuario.getTexto().trim();
        String tipo = campoTipo.getTexto().trim();
        String email = campoEmail.getTexto().trim();
        String contrasenia = campoContrasenia.getTexto().trim();
//        String nombreCompleto = nombre + " " + apellido;

        if (dni.isEmpty() || nombre.isEmpty() || apellido.isEmpty() || nombreUsuario.isEmpty() || tipo.isEmpty() || email.isEmpty() || contrasenia.isEmpty()) {
            try {
                throw new UsuarioExcepction("Todos los campos deben estar completos.");
            } catch (UsuarioExcepction e) {
                throw new RuntimeException(e);
            }
        }

        try {
            RolUsuario rolUsuario = RolUsuario.valueOf(tipo.toUpperCase());

            Usuario usuario;
            switch (rolUsuario) {
                case ADMINISTRADOR -> usuario = new Administrador(dni, nombre, apellido, email, rolUsuario, nombreUsuario, contrasenia);
                case ALUMNO -> usuario = new Alumno(dni, nombre, apellido, email, rolUsuario, nombreUsuario, contrasenia);
                case PROFESOR -> usuario = new Profesor(dni, nombre, apellido, email, rolUsuario, nombreUsuario,contrasenia );
                default -> throw new UsuarioExcepction("Rol desconocido: " + tipo);
            }

            return usuario;

        } catch (IllegalArgumentException e) {
            try {
                throw new UsuarioExcepction("Rol inválido: " + tipo);
            } catch (UsuarioExcepction ex) {
                throw new RuntimeException(ex);
            }
        } catch (UsuarioExcepction e) {
            throw new RuntimeException(e);
        }
    }
}
