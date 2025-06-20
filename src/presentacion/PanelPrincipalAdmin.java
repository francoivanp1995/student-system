package presentacion;

import datos.interfaz.Panel;
import datos.interfaz.VistaLogin;
import presentacion.abstracto.PanelBase;

import javax.swing.*;

public class PanelPrincipalAdmin extends PanelBase implements Panel {

    protected final String crearCurso  = "Crear Curso";
    protected final String crearAlumno  = "Crear Alumno";
    protected final String cerrarSesion  = "CerrarSesion";
    protected final String crearCursoCommand  = "CURSO";
    protected final String crearAlumnoCommand  = "ALUMNO";
    protected final String cerrarSesionCommand  = "CERRAR";

    public PanelPrincipalAdmin(PanelManager panelManager) {
        super(panelManager);

    }

    @Override
    protected JPanel construirPanel() {
        return null;
    }

    @Override
    protected void setUIComponentes() {

    }

    @Override
    protected JPanel crearTitulo() {
        return null;
    }

    @Override
    public void crearBotonera() {
        panelBotonera = new PanelBotonera();
        panelBotonera.agregarBoton(crearCurso,crearCursoCommand);
        panelBotonera.agregarBoton(crearAlumno,crearAlumnoCommand);
        panelBotonera.agregarBoton(cerrarSesion,cerrarSesionCommand);

        setBotonera(panelBotonera);
    }
}
