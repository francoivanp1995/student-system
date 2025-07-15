package presentacion.admin;

import Servicios.ServicioCurso;
import datos.Curso;
import datos.Excepcion.CursoException;
import presentacion.PanelCampoFormulario;
import presentacion.abstracto.PanelFormularioBase;

import java.awt.*;

public class PanelFormularioCursoCrear extends PanelFormularioBase<Curso> {

    private final PanelCampoFormulario campoNombre;
    private final PanelCampoFormulario campoCupo;
    private final PanelCampoFormulario campoPrecio;
    private final PanelCampoFormulario campoNotaAprobacion;
    private final PanelCampoFormulario campoProfesor;

    public PanelFormularioCursoCrear() {
        this(null);
    }

    public PanelFormularioCursoCrear(Curso curso) {
        setLayout(new GridLayout(5, 1, 10, 10));

        campoNombre = new PanelCampoFormulario("Nombre:");
        campoCupo = new PanelCampoFormulario("Cupo:");
        campoPrecio = new PanelCampoFormulario("Precio:");
        campoNotaAprobacion = new PanelCampoFormulario("Nota de Aprobación:");
        campoProfesor = new PanelCampoFormulario("Profesor:");

        add(campoNombre);
        add(campoCupo);
        add(campoPrecio);
        add(campoNotaAprobacion);
        add(campoProfesor);

        if (curso != null) {
            campoNombre.setTexto(curso.getNombre());
            campoNombre.setEditable(false);
            campoCupo.setTexto(String.valueOf(curso.getCupoMaximo()));
            campoPrecio.setTexto(String.valueOf(curso.getPrecio()));
            campoNotaAprobacion.setTexto(String.valueOf(curso.getNotaAprobacion()));
            campoProfesor.setTexto(curso.getId());
        }
    }

    @Override
    public Curso construirUIForm() {
        ServicioCurso servicioCurso = new ServicioCurso();
        return servicioCurso.crearCurso(
                campoNombre.getTexto().trim().toUpperCase(),
                campoCupo.getTexto().trim().toUpperCase(),
                campoPrecio.getTexto().trim().toUpperCase(),
                campoNotaAprobacion.getTexto().trim().toUpperCase(),
                campoProfesor.getTexto().trim().toUpperCase()
        );
    }
}
