package presentacion.admin;

import datos.Curso;
import datos.Excepcion.CursoException;
import presentacion.PanelCampoFormulario;
import presentacion.abstracto.PanelFormularioBase;

import java.awt.*;

public class PanelFormularioCurso extends PanelFormularioBase<Curso> {

    private final PanelCampoFormulario campoNombre;
    private final PanelCampoFormulario campoCupo;
    private final PanelCampoFormulario campoPrecio;
    private final PanelCampoFormulario campoNotaAprobacion;
    private final PanelCampoFormulario campoProfesor;

    public PanelFormularioCurso() {
        setLayout(new GridLayout(5, 1, 10, 10));

        campoNombre = new PanelCampoFormulario("Nombre:");
        campoCupo = new PanelCampoFormulario("Cupo:");
        campoPrecio = new PanelCampoFormulario("Precio:");
        campoNotaAprobacion = new PanelCampoFormulario("Nota de Aprobación:");
        campoProfesor = new PanelCampoFormulario("Profesor");

        add(campoNombre);
        add(campoCupo);
        add(campoPrecio);
        add(campoNotaAprobacion);
        add(campoProfesor);
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
            System.out.println("LLEGO AL TRY DEL CONSTRUIRENTIDAD");
            System.out.println("cupo" + cupo);
            System.out.println("precio" + precio);
            System.out.println("Nota" + notaAprobacion);
            System.out.println("Profesor" + profesorDni);
            System.out.println("nombre" + nombre);
            return new Curso(nombre, cupo, precio, notaAprobacion, profesorDni);
        } catch (NumberFormatException e) {
            throw new CursoException("Cupo, precio y nota deben ser números válidos.");
        }
    }
}
