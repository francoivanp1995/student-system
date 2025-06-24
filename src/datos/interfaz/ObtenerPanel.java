package datos.interfaz;

import presentacion.*;
import presentacion.admin.PanelPrincipalAdmin;
import presentacion.admin.PanelPrincipalGestionar;
import presentacion.alumno.PanelPrincipalAlumno;
import presentacion.profesor.PanelPrincipalProfesor;

public interface ObtenerPanel {

    PanelInicio getPanelInicio();
    PanelPrincipalAdmin getPanelPrincipalAdmin();
    PanelPrincipalAlumno getPanelPrincipalAlumno();
    PanelPrincipalProfesor getPanelPrincipalProfesor();
    PanelPrincipalGestionar getPanelGestionarCurso();
    PanelPrincipalGestionar getPanelGestionarUsuario();
}
