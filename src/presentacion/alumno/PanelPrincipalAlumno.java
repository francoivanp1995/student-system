package presentacion.alumno;

import presentacion.PanelBotonera;
import presentacion.PanelManager;
import presentacion.abstracto.PanelBase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static datos.Comandos.*;
import static datos.Comandos.USUARIO;

public class PanelPrincipalAlumno extends PanelBase {

    private PanelBotonera botoneraSur;
    private PanelBotonera botoneraCentro;
    private static final String textoVerCurso  = "VER CURSOS";
    private static final String labelPanel = "PANEL ALUMNO";

    public PanelPrincipalAlumno(PanelManager panelManager) {
        super(panelManager);
        setUIComponentesBase();
    }

    @Override
    protected void agregarBotonABotonera() {
        botoneraSur.agregarBoton(REGRESAR,REGRESAR);
        botoneraSur.agregarBoton(CERRAR, CERRAR);
        botoneraCentro.agregarBoton(textoVerCurso,CURSO);
        botoneraCentro.agregarBoton(MISINSCRIPCIONES,MISINSCRIPCIONES);
    }

    @Override
    protected JPanel panelCentral() {
        JPanel centro = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 40));
        centro.add(botoneraCentro);
        return centro;
    }

    @Override
    public void setListener(ActionListener listener) {
        botoneraCentro.agregarListener(listener);
        botoneraSur.agregarListener(listener);
    }

    @Override
    protected void setUIComponentesBase() {
        botoneraCentro = new PanelBotonera();
        botoneraSur = new PanelBotonera();
        agregarBotonABotonera();
        add(panelSuperior(), BorderLayout.NORTH);
        add(panelCentral(), BorderLayout.CENTER);
        add(botoneraSur, BorderLayout.SOUTH);
    }

    @Override
    protected JPanel panelSuperior() {

        JPanel tituloPanel = new JPanel();
        JLabel nombreTitulo = new JLabel(labelPanel);
        tituloPanel.add(nombreTitulo);
        return tituloPanel;
    }
}
