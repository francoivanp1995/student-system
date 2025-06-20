package datos.interfaz;

import presentacion.PanelInicio;
import presentacion.PanelPrincipalAdmin;
import presentacion.PanelPrincipalAlumno;
import presentacion.PanelPrincipalProfesor;

public interface ObtenerPanel {

    PanelInicio getPanelInicio();
    PanelPrincipalAdmin getPanelPrincipalAdmin();
    PanelPrincipalAlumno getPanelPrincipalAlumno();
    PanelPrincipalProfesor getPanelPrincipalProfesor();
}
