package presentacion.alumno;

import Servicios.ServicioAlumno;
import datos.*;
import datos.Excepcion.PanelException;
import presentacion.PanelManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static datos.Comandos.*;

public class ControladorPanelCursosAlumno implements ActionListener {

    private final PanelCursosAlumno panelCursosAlumno;
    private final PanelManager panelManager;
    private final ServicioAlumno servicioAlumno;

    public ControladorPanelCursosAlumno(PanelCursosAlumno panel, PanelManager manager, ServicioAlumno servicioAlumno) {
        this.panelCursosAlumno = panel;
        this.panelManager = manager;
        this.servicioAlumno = servicioAlumno;
        this.panelCursosAlumno.setListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case INSCRIBIRSE:
                inscribirseACurso();
                break;
            case DESINSCRIBIRSE:
                desincribirseCurso();
                break;
            case REGRESAR:
                try {
                    panelManager.mostrarPanelPorRol(RolUsuario.ALUMNO);
                } catch (PanelException ex) {
                    panelCursosAlumno.mostrarError("Error al regresar: " + ex.getMessage());
                }
                break;
            case CERRAR:
                System.exit(0);
                break;
        }
    }

    private void inscribirseACurso() {
        int filaSeleccionada = panelCursosAlumno.getFilaSeleccionada();
        if (filaSeleccionada < 0) {
            panelCursosAlumno.mostrarError("Seleccione un curso para inscribirse.");
            return;
        }
        try {
            Curso curso = ((CursoAlumnoTableModel) panelCursosAlumno.getTabla().getModel()).getCursoAt(filaSeleccionada);
            servicioAlumno.inscribirACurso(panelManager.getUsuarioLogueado(), curso);
            panelCursosAlumno.mostrarInfo("Inscripción exitosa en el curso: " + curso.getNombre());
        } catch (Exception ex) {
            panelCursosAlumno.mostrarError("Error al inscribirse: " + ex.getMessage());
        }
    }

    private void desincribirseCurso(){
        int filaSeleccionada = panelCursosAlumno.getFilaSeleccionada();
        if (filaSeleccionada < 0) {
            panelCursosAlumno.mostrarError("Seleccione un curso para inscribirse.");
            return;
        }
        try {
            Curso curso = ((CursoAlumnoTableModel) panelCursosAlumno.getTabla().getModel()).getCursoAt(filaSeleccionada);
            servicioAlumno.desinscribirDeCurso(panelManager.getUsuarioLogueado(), curso);
            panelCursosAlumno.mostrarInfo("Desinscripcion exitosa en el curso: " + curso.getNombre());
        } catch (Exception ex) {
            panelCursosAlumno.mostrarError("Error al inscribirse: " + ex.getMessage());
        }
    }

//    private void cargarDatos() {
//        System.out.println(">> Cargando datos para: " + tipo);
//        switch (Gestionar.CURSO) {
//            case CURSO -> {
//                List<Curso> cursos = servicioAdmin.obtenerTodosLosCursos();
//                panel.actualizarModelo(new CursoAlumnoTableModel(servicioAdmin.obtenerTodosLosCursos()));
//            }
//            case USUARIO -> {
//                List<Usuario> usuarios = servicioAdmin.obtenerTodosLosUsuarios();
//                panel.actualizarModelo(new UsuarioTableModel(servicioAdmin.obtenerTodosLosUsuarios()));
//            }
//        }
//    }
}