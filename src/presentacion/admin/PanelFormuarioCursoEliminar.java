package presentacion.admin;

import datos.Curso;
import datos.Excepcion.CursoException;
import presentacion.PanelCampoFormulario;
import presentacion.abstracto.PanelFormularioBase;

import java.awt.*;

public class PanelFormuarioCursoEliminar extends PanelFormularioBase<String> {

    private final PanelCampoFormulario campoNombre;

    public PanelFormuarioCursoEliminar() {
        setLayout(new GridLayout(1, 1, 10, 10));
        campoNombre = new PanelCampoFormulario("Nombre del curso a eliminar:");
        add(campoNombre);
    }

    @Override
    public String construirEntidad() {
        String nombre = campoNombre.getTexto().trim();
        if (nombre.isEmpty()) {
            throw new CursoException("Debe ingresar un nombre.");
        }
        return nombre;
    }
}
