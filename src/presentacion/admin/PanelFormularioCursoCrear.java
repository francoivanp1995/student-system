package presentacion.admin;

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
        this(null); // Reutiliza el constructor que acepta Curso
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
    public Curso construirEntidad() {
        String nombre = campoNombre.getTexto().trim();
        String textoCupo = campoCupo.getTexto().trim();
        String textoPrecio = campoPrecio.getTexto().trim();
        String textoNota = campoNotaAprobacion.getTexto().trim();
        String profesorDni = campoProfesor.getTexto().trim();

        if (nombre.isEmpty() || textoCupo.isEmpty() || textoPrecio.isEmpty() || textoNota.isEmpty() || profesorDni.isEmpty()) {
            throw new CursoException("Todos los campos deben estar completos.");
        }
        try {
            int cupo = Integer.parseInt(textoCupo);
            int precio = Integer.parseInt(textoPrecio);
            int notaAprobacion = Integer.parseInt(textoNota);
            return new Curso(nombre, cupo, precio, notaAprobacion, profesorDni);
        } catch (NumberFormatException e) {
            throw new CursoException("Cupo, precio y nota deben ser números válidos.");
        }
    }
}
