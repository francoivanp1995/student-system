package presentacion.admin;

import presentacion.PanelBotonera;
import presentacion.PanelManager;
import presentacion.abstracto.PanelBase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static datos.Comandos.*;

public class PanelPrincipalAdmin extends PanelBase {

    private static final String TextoGestionarCurso  = "GESTIONAR CURSO";
    private static final String TextoGestionarUsuario  = "GESTIONAR USUARIO";
    private static final String labelPanel = "PANEL ADMINISTRADOR";
    private PanelBotonera botoneraCentro;
    private PanelBotonera botoneraSur;

    public PanelPrincipalAdmin(PanelManager panelManager) {
        super(panelManager);
        setUIComponentesBase();
    }

    @Override
    protected void agregarBotonABotonera() {
        botoneraSur.agregarBoton(REGRESAR,REGRESAR);
        botoneraSur.agregarBoton(CANCELAR,CANCELAR);
        botoneraCentro.agregarBoton(TextoGestionarCurso,CURSO);
        botoneraCentro.agregarBoton(TextoGestionarUsuario,USUARIO);
    }

    @Override
    protected JPanel panelCentral() {
        JPanel centro = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 40));
        centro.add(botoneraCentro);
        return centro;
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

    public void setListener(ActionListener listener) {
        System.out.println("Estoy en el listener de Panel Principal Admin");
        botoneraCentro.agregarListener(listener);
        botoneraSur.agregarListener(listener);
    }
}
