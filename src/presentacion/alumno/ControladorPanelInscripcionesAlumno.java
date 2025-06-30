package presentacion.alumno;

import datos.Excepcion.PanelException;
import datos.RolUsuario;
import presentacion.PanelManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static datos.Comandos.*;
import static datos.Comandos.CERRAR;

public class ControladorPanelInscripcionesAlumno implements ActionListener {

    private static PanelManager panelManager;
    private static PanelInscripcionesAlumno panelInscripcionesAlumno;

    public ControladorPanelInscripcionesAlumno(PanelInscripcionesAlumno panelInscripcionesAlumno, PanelManager panelManager){
        this.panelManager = panelManager;
        this.panelInscripcionesAlumno = panelInscripcionesAlumno;
        this.panelInscripcionesAlumno.setListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case REGRESAR:
                try {
                    panelManager.mostrarPanelPorRol(RolUsuario.ALUMNO);
                } catch (PanelException ex) {
                    panelInscripcionesAlumno.mostrarError("Error al regresar: " + ex.getMessage());
                }
                break;
            case CERRAR:
                System.exit(0);
                break;
        }
    }
}
