package presentacion.admin;

import Servicios.ServicioUsuario;
import datos.*;
import datos.Excepcion.*;
import Servicios.ServicioAdmin;
import presentacion.abstracto.FormularioUtilidad;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.util.List;

import static datos.Comandos.*;

public class ControladorPanelPrincipalGestionar implements ActionListener {

    private final PanelPrincipalGestionar panel;
    private final ServicioAdmin servicioAdmin;
    private final ServicioUsuario servicioUsuario;
    private final Gestionar tipo;
    private RolUsuario rol;

    public ControladorPanelPrincipalGestionar(PanelPrincipalGestionar panel, ServicioAdmin servicioAdmin, Gestionar tipo, RolUsuario rol, ServicioUsuario servicioUsuario) {
        this.panel = panel;
        this.servicioAdmin = servicioAdmin;
        this.tipo = tipo;
        this.rol = rol;
        this.servicioUsuario = servicioUsuario;
        this.panel.setListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case ACTUALIZAR -> {
                if (tipo == Gestionar.CURSO) {
                    actualizarCurso();
                    cargarDatos();
                } else if (tipo == Gestionar.USUARIO){
                    actualizarUsuario();
                    cargarDatos();
                }
            }
            case CREAR -> {
                if (tipo == Gestionar.CURSO) {
                    crearCurso();
                    cargarDatos();
                } else if (tipo == Gestionar.USUARIO) {
                    crearUsuario();
                    cargarDatos();
                }
            }
            case ELIMINAR -> {
                if (tipo == Gestionar.CURSO){
                    eliminarCurso();
                    cargarDatos();
                } else if (tipo == Gestionar.USUARIO) {
                    eliminarUsuario();
                    cargarDatos();
                }
            }
            case REGRESAR -> {
                try {
                    panel.getPanelManager().mostrarPanelPorRol(rol);
                } catch (Exception ex) {
                    panel.mostrarError("Error al regresar: " + ex.getMessage());
                }
            }
            case MOSTRARREPORTE -> {
                cargarDatos();
            }
            case CERRAR -> System.exit(0);
            case EXPORTARREPORTE -> {
                if (tipo == Gestionar.CURSO){
                    exportarCurso();
                }
            }
        }
    }

    private void cargarDatos() {
        switch (tipo) {
            case CURSO -> {
                List<Curso> cursos = servicioAdmin.obtenerTodosLosCursos();
                panel.actualizarModelo(new CursoAdminTableModel(servicioAdmin.obtenerTodosLosCursos()));
            }
            case USUARIO -> {
                List<Usuario> usuarios = servicioAdmin.obtenerTodosLosUsuarios();
                panel.actualizarModelo(new UsuarioTableModel(servicioAdmin.obtenerTodosLosUsuarios()));
            }
        }
    }

    private void crearCurso() {
        try {
            Curso nuevoCurso = FormularioUtilidad.mostrarFormulario(new PanelFormularioCursoCrear(), "Crear nuevo curso");
            if (nuevoCurso != null) {
                servicioAdmin.validarCurso(nuevoCurso);
                servicioAdmin.crearCurso(nuevoCurso);
                panel.mostrarInfo("Curso creado exitosamente.");
            }
        } catch (ServicioException e) {
            panel.mostrarError("Error al guardar el curso " + e.getMessage());
        } catch (Exception e) {
            panel.mostrarError("Error inesperado: " + e.getMessage());
        }
    }

    private void eliminarCurso() {
        try {
            int fila = panel.getFilaSeleccionada();
            if (fila >= 0) {
                Curso curso = ((CursoAdminTableModel) panel.getTabla().getModel()).getCursoAt(fila);
                servicioAdmin.eliminarCurso(curso.getNombre());
                panel.mostrarInfo("Curso eliminado exitosamente.");
            } else {
                panel.mostrarAdvertencia("Debe seleccionar un curso para eliminar.");
            }
        } catch (ServicioException e) {
            panel.mostrarError("Error al eliminar el curso: " + e.getMessage());
        } catch (Exception e) {
            panel.mostrarError("Error inesperado: " + e.getMessage());
        }
    }

    private void actualizarCurso() {
        try {
            int fila = panel.getFilaSeleccionada();
            if (fila >= 0) {
                Curso cursoOriginal = ((CursoAdminTableModel) panel.getTabla().getModel()).getCursoAt(fila);
                Curso cursoActualizado = FormularioUtilidad.mostrarFormulario(new PanelFormularioCursoCrear(cursoOriginal),"Actualizar curso");
                if (cursoActualizado != null) {
                    servicioAdmin.validarCurso(cursoActualizado);
                    servicioAdmin.actualizarCurso(cursoActualizado);
                    panel.mostrarInfo("Curso actualizado exitosamente.");
                }
            } else {
                panel.mostrarAdvertencia("Debe seleccionar un curso para actualizar.");
            }
        } catch (ServicioException e) {
            panel.mostrarError(e.getMessage());
        } catch (Exception e) {
            panel.mostrarError("Error inesperado: " + e.getMessage());
        }
    }

    private void crearUsuario() {
        try {
            Usuario nuevoUsuario = FormularioUtilidad.mostrarFormulario(new PanelFormularioUsuarioCrear(), "Crear nuevo Usuario");
            if (nuevoUsuario != null) {
                servicioUsuario.validarUsuario(nuevoUsuario);
                servicioAdmin.crearUsuario(nuevoUsuario);
            }
        } catch (ServicioException e) {
            panel.mostrarError("Error al guardar el Usuario." + e.getMessage());
        } catch (Exception e) {
            panel.mostrarError("Error inesperado: " + e.getMessage());
        }
    }


    private void eliminarUsuario(){
        try {
            int fila = panel.getFilaSeleccionada();
            if (fila >= 0) {
                Usuario usuario = ((UsuarioTableModel) panel.getTabla().getModel()).getUsuarioAt(fila);
                servicioAdmin.eliminarUsuario(usuario);
                panel.mostrarInfo("Usuario eliminado exitosamente.");
            } else {
                panel.mostrarError("Debe seleccionar un usuario para eliminar.");
            }
        } catch (ServicioException e) {
            panel.mostrarError("Error al eliminar el curso: " + e.getMessage());
        } catch (Exception e) {
            panel.mostrarError("Error inesperado: " + e.getMessage());
        }
    }

    private void actualizarUsuario() {
        try {
            int fila = panel.getFilaSeleccionada();
            if (fila >= 0) {
                Usuario usuarioOriginal = ((UsuarioTableModel) panel.getTabla().getModel()).getUsuarioAt(fila);
                Usuario usuarioActualizado = FormularioUtilidad.mostrarFormulario(new PanelFormularioUsuarioCrear(usuarioOriginal),"Actualizar usuario");
                if (usuarioActualizado != null) {
                    servicioUsuario.validarUsuario(usuarioActualizado);
                    servicioAdmin.actualizarUsuario(usuarioActualizado);
                    panel.mostrarInfo("Usuario actualizado exitosamente.");
                }
            } else {
                panel.mostrarAdvertencia("Debe seleccionar un curso para actualizar.");
            }
        } catch (ServicioException e) {
            panel.mostrarError("Error al actualizar el curso: " + e.getMessage());
        } catch (Exception e) {
            panel.mostrarError("Error inesperado: " + e.getMessage());
        }
    }

    private void exportarCurso() {
        try {
            // Pedir archivo al usuario
            JFileChooser fileChooser = new JFileChooser();
            int opcion = fileChooser.showSaveDialog(panel);
            if (opcion == JFileChooser.APPROVE_OPTION) {
                File archivo = fileChooser.getSelectedFile();

                List<Curso> cursos = servicioAdmin.obtenerCursosConCantidadInscritos();

                try (FileWriter writer = new FileWriter(archivo)) {
                    writer.write("Nombre Curso,Anotados,Recaudación\n");

                    for (Curso curso : cursos) {
                        int anotados = curso.getCantidadInscritos();
                        double recaudacion = anotados * curso.getPrecio();

                        writer.write(String.format("%s,%d,%.2f\n",
                                curso.getNombre(),
                                anotados,
                                recaudacion));
                    }
                }
                panel.mostrarInfo("Reporte exportado exitosamente a " + archivo.getAbsolutePath());
            }
        } catch (Exception e) {
            panel.mostrarError("Error al exportar reporte: " + e.getMessage());
        }
    }
}