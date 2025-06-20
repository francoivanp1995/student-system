package presentacion;

import datos.interfaz.Panel;
import datos.interfaz.VistaLogin;
import presentacion.abstracto.PanelBase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class PanelPrincipalAdmin extends PanelBase implements Panel {

    protected final String crearCurso  = "Crear Curso";
    protected final String crearAlumno  = "Crear Alumno";
    protected final String cerrarSesion  = "CerrarSesion";
    protected final String crearCursoCommand  = "CURSO";
    protected final String crearAlumnoCommand  = "ALUMNO";
    protected final String cerrarSesionCommand  = "CERRAR";
    protected final String regresar = "Regresar";
    protected final String regresarCommand  = "Regresar";
    protected final String cancelar = "Cancelar";
    protected final String cancelarCommand  = "CANCELAR";

    private PanelBotonera botoneraCentro;
    private PanelBotonera botoneraSur;

    public PanelPrincipalAdmin(PanelManager panelManager) {
        super(panelManager);
        crearBotonera();
        setUIComponentes();
    }

    @Override
    protected JPanel construirPanel() {
        JPanel centro = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 40));
        centro.add(botoneraCentro);
        return centro;
    }

    @Override
    protected void setUIComponentes() {
        add(crearTitulo(), BorderLayout.NORTH);
        add(construirPanel(), BorderLayout.CENTER);
    }

    @Override
    protected JPanel crearTitulo() {
        JPanel tituloPanel = new JPanel();
        JLabel nombreTitulo = new JLabel("Panel Administrador");
        tituloPanel.add(nombreTitulo);
        return tituloPanel;
    }

    @Override
    public void crearBotonera() {
        botoneraSur = new PanelBotonera();
        botoneraSur.agregarBoton(regresar,regresarCommand);
        botoneraSur.agregarBoton(cancelar,cancelarCommand);

        System.out.println("Botonera inferior creada: " + botoneraSur);
        setBotonera(botoneraSur);

        botoneraCentro = new PanelBotonera();
        botoneraCentro.agregarBoton(crearCurso,crearCursoCommand);
        botoneraCentro.agregarBoton(crearAlumno,crearAlumnoCommand);
        System.out.println("Botonera centro creada: " + botoneraCentro);

    }

    public void setListener(ActionListener listener) {
        botoneraCentro.agregarListener(listener);
        botoneraSur.agregarListener(listener);
    }
}
