package presentacion.alumno;

import Servicios.ServicioAlumno;
import datos.Excepcion.PanelException;
import datos.Gestionar;
import datos.RolUsuario;
import presentacion.PanelManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static datos.Comandos.*;
import static datos.Comandos.USUARIO;

public class ControladorPanelPrincipalAlumno implements ActionListener {

    private PanelManager panelManager;
//    private final ServicioAdmin servicioAdmin;
    private final PanelPrincipalAlumno panelPrincipalAlumno;
    private final ServicioAlumno servicioAlumno;


    public ControladorPanelPrincipalAlumno(PanelPrincipalAlumno panelPrincipalAlumno, PanelManager panelManager) {
        this.panelPrincipalAlumno = panelPrincipalAlumno;
        this.panelManager = panelManager;
        this.servicioAlumno = new ServicioAlumno(panelPrincipalAlumno,panelManager);
        this.panelPrincipalAlumno.setListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case CURSO:
                try {
                    panelManager.mostrarPanelCursosAlumno(); // este método debes implementarlo en PanelManager
                } catch (PanelException ex) {
                    ex.printStackTrace();
                    panelPrincipalAlumno.mostrarError("Error al mostrar cursos: " + ex.getMessage());
                }
                break;
            case MISINSCRIPCIONES: {
                try {
                    panelManager.mostrarMisInscripciones();
                } catch (PanelException ex) {
                    ex.printStackTrace();
                    panelPrincipalAlumno.mostrarError("Error al mostrar mis inscripciones" + ex.getMessage());
                }
                break;
            }
            case REGRESAR:
                try {
                    panelManager.mostrarPanelPorRol(RolUsuario.ALUMNO);
                } catch (PanelException ex) {
                    ex.printStackTrace();
                    panelPrincipalAlumno.mostrarError("Error al regresar: " + ex.getMessage());
                }
                break;
            case CERRAR:
                System.exit(0);
                break;
        }
    }
}
