package presentacion.admin;

import presentacion.PanelBotonera;
import presentacion.PanelManager;
import presentacion.abstracto.PanelBase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static datos.Comandos.*;

public class PanelPrincipalAdmin extends PanelBase {

    private final String TextoGestionarCurso  = "GESTIONAR CURSO";
    private final String TextoGestionarUsuario  = "GESTIONAR USUARIO";
    private final String regresar = "Regresar";
    private final String cancelar = "Cancelar";
    private final String regresarCommand  = REGRESAR;
    private final String TextoGestionarCursoCommand  = CURSO;
    private final String TextoGestionarUsuarioCommand  = USUARIO;
    private final String cancelarCommand  = CANCELAR;

    private PanelBotonera botoneraCentro;
    private PanelBotonera botoneraSur;

    public PanelPrincipalAdmin(PanelManager panelManager) {
        super(panelManager);
        setUIComponentesBase();
    }

    @Override
    protected void agregarBotonABotonera() {
        botoneraSur.agregarBoton(regresar,regresarCommand);
        botoneraSur.agregarBoton(cancelar,cancelarCommand);
        botoneraCentro.agregarBoton(TextoGestionarCurso,TextoGestionarCursoCommand);
        botoneraCentro.agregarBoton(TextoGestionarUsuario,TextoGestionarUsuarioCommand);
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
        JLabel nombreTitulo = new JLabel("Panel Administrador");
        tituloPanel.add(nombreTitulo);
        return tituloPanel;
    }

    public void setListener(ActionListener listener) {
        System.out.println("Estoy en el listener de Panel Principal Admin");
        botoneraCentro.agregarListener(listener);
        botoneraSur.agregarListener(listener);
    }
}
