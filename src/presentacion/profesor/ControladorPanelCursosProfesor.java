package presentacion.profesor;

import Servicios.ServicioAlumno;
import Servicios.ServicioProfesor;
import datos.Excepcion.PanelException;
import datos.RolUsuario;
import presentacion.PanelManager;
import presentacion.alumno.PanelCursosAlumno;
import presentacion.alumno.PanelInscripcionesAlumno;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static datos.Comandos.CERRAR;
import static datos.Comandos.REGRESAR;

public class ControladorPanelCursosProfesor implements ActionListener  {

    private final PanelCursosProfesor panelCursosProfesor;
    private final PanelManager panelManager;
    private final ServicioProfesor servicioProfesor;

    public ControladorPanelCursosProfesor(PanelCursosProfesor panelCursosProfesor, PanelManager panelManager, ServicioProfesor servicioProfesor){
        this.panelManager = panelManager;
        this.panelCursosProfesor = panelCursosProfesor;
        this.servicioProfesor = servicioProfesor;
        this.panelCursosProfesor.setListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case REGRESAR:
                try {
                    panelManager.mostrarPanelPorRol(RolUsuario.PROFESOR);
                } catch (PanelException ex) {
                    ex.printStackTrace();
                    panelCursosProfesor.mostrarError("Error al regresar");
                }
                break;
            case CERRAR:
                System.exit(0);
                break;
        }
    }
}
