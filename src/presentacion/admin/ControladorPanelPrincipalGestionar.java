package presentacion.admin;

import datos.*;
import datos.Excepcion.*;
import presentacion.PanelManager;
import Servicios.ServicioAdmin;
import presentacion.abstracto.FormularioUtilidad;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import static datos.Comandos.*;

public class ControladorPanelPrincipalGestionar implements ActionListener {

    private final PanelPrincipalGestionar panel;
    private final ServicioAdmin servicioAdmin;
    private final Gestionar tipo;

    private RolUsuario rol;

    //chequear si esto funciona con panelManager.
    private PanelManager panelManager;


    public ControladorPanelPrincipalGestionar(PanelPrincipalGestionar panel, ServicioAdmin servicioAdmin, Gestionar tipo, RolUsuario rol) {
        System.out.println(">> Creando ControladorGestionar para: " + tipo);
        this.panel = panel;
        this.servicioAdmin = servicioAdmin;
        this.tipo = tipo;
        this.rol = rol;
        this.panel.setListener(this);
    }

    private void cargarDatos() {
        System.out.println(">> Cargando datos para: " + tipo);
        switch (tipo) {
            case CURSO -> {
                List<Curso> cursos = servicioAdmin.obtenerTodosLosCursos();
                panel.actualizarModelo(new CursoTableModel(servicioAdmin.obtenerTodosLosCursos()));
            }
            case USUARIO -> {
                //Esto no es pedido. Pero podria ser bueno.
//                List<Usuario> usuarios = servicio.obtenerTodosLosUsuarios();
//                panel.actualizarModelo(new UsuarioTableModel(usuarios));
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case ACTUALIZAR -> {
                if (tipo == Gestionar.CURSO) {
                    actualizarCurso();
                    cargarDatos();
                } else if (tipo == Gestionar.USUARIO){

                }
            }
            case CREAR -> {
                if (tipo == Gestionar.CURSO) {
                    crearCurso();
                    cargarDatos();
                } else if (tipo == Gestionar.USUARIO) {
//                    servicio.crearUsuario(new Usuario("nuevo", "user", "pass", null));
                }

            }
            case ELIMINAR -> {
                if (tipo == Gestionar.CURSO){
                    eliminarCurso();
                    cargarDatos();
                } else if (tipo == Gestionar.USUARIO) {
                    
                }
            }
            case REGRESAR -> {
                try {
                    panel.getPanelManager().mostrarPanelPorRol(rol);
                } catch (Exception ex) {
                    panel.mostrarError("Error al regresar: " + ex.getMessage());
                }
            }
            case "MOSTRARREPORTE" -> {
                cargarDatos();
            }
            case CANCELAR -> System.exit(0);
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
        } catch (CursoException e) {
            panel.mostrarError("Validación: " + e.getMessage());
        } catch (ServicioException e) {
            panel.mostrarError("Error al guardar el curso: " + e.getMessage());
        } catch (Exception e) {
            panel.mostrarError("Error inesperado: " + e.getMessage());
        }
    }

    private void eliminarCurso() {
        try {
            int fila = panel.getFilaSeleccionada();
            System.out.println("Fila seleccionada: " + fila);

            if (fila >= 0) {
                Curso curso = ((CursoTableModel) panel.getTabla().getModel()).getCursoAt(fila);
                System.out.println("Curso seleccionado: " + curso);
                System.out.println("Nombre del curso: " + curso.getNombre());
                servicioAdmin.eliminarCurso(curso.getNombre());
                panel.mostrarInfo("Curso eliminado exitosamente.");
            } else {
                panel.mostrarError("Debe seleccionar un curso para eliminar.");
            }
        } catch (CursoException e) {
            panel.mostrarError("Validación: " + e.getMessage());
        } catch (ServicioException e) {
            panel.mostrarError("Error al eliminar el curso: " + e.getMessage());
        } catch (Exception e) {
            panel.mostrarError("Error inesperado: " + e.getMessage());
        }
    }

    private void actualizarCurso() {
        try {
            int fila = panel.getFilaSeleccionada();
            System.out.println("Fila seleccionada: " + fila);

            if (fila >= 0) {
                Curso cursoOriginal = ((CursoTableModel) panel.getTabla().getModel()).getCursoAt(fila);
                System.out.println("Curso seleccionado: " + cursoOriginal);

                Curso cursoActualizado = FormularioUtilidad.mostrarFormulario(
                        new PanelFormularioCursoCrear(cursoOriginal),
                        "Actualizar curso"
                );

                if (cursoActualizado != null) {
                    servicioAdmin.validarCurso(cursoActualizado);
                    servicioAdmin.actualizarCurso(cursoActualizado);
                    panel.mostrarInfo("Curso actualizado exitosamente.");
                }
            } else {
                panel.mostrarError("Debe seleccionar un curso para actualizar.");
            }
        } catch (CursoException e) {
            panel.mostrarError("Validación: " + e.getMessage());
        } catch (ServicioException e) {
            panel.mostrarError("Error al actualizar el curso: " + e.getMessage());
        } catch (Exception e) {
            panel.mostrarError("Error inesperado: " + e.getMessage());
        }
    }
}