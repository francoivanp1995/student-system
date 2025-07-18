package presentacion.profesor;

import Servicios.ServicioAlumno;
import Servicios.ServicioProfesor;
import datos.Excepcion.PanelException;
import datos.RolUsuario;
import presentacion.PanelManager;
import presentacion.alumno.PanelPrincipalAlumno;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static datos.Comandos.*;
import static datos.Comandos.CERRAR;

public class ControladorPanelPrincipalProfesor implements ActionListener {

    private PanelManager panelManager;
    private final PanelPrincipalProfesor panelPrincipalProfesor;
    private final ServicioProfesor servicioProfesor;


    public ControladorPanelPrincipalProfesor(PanelPrincipalProfesor panelPrincipalProfesor, PanelManager panelManager) {
        this.panelPrincipalProfesor = panelPrincipalProfesor;
        this.panelManager = panelManager;
        this.servicioProfesor = new ServicioProfesor(panelPrincipalProfesor,panelManager);
        this.panelPrincipalProfesor.setListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case CURSO:
                try {
                    panelManager.mostrarPanelCursosProfesor();
                } catch (PanelException ex) {
                    ex.printStackTrace();
                    panelPrincipalProfesor.mostrarError("Error al mostrar cursos: " + ex.getMessage());
                }
                break;
            case ALUMNOS: {
                try {
                    panelManager.mostrarAlumnosDeMisCursos();
                } catch (PanelException ex) {
                    ex.printStackTrace();
                    panelPrincipalProfesor.mostrarError("Error al mostrar mis inscripciones" + ex.getMessage());
                }
                break;
            }
            case REGRESAR:
                try {
                    panelManager.mostrarPanelPorRol(RolUsuario.PROFESOR);
                } catch (PanelException ex) {
                    ex.printStackTrace();
                    panelPrincipalProfesor.mostrarError("Error al regresar: " + ex.getMessage());
                }
                break;
            case CERRAR:
                System.exit(0);
                break;
        }
    }
}
