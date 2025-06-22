package presentacion;

import presentacion.abstracto.PanelBase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class PanelPrincipalAdmin extends PanelBase {

    protected final String TextoGestionarCurso  = "GESTIONAR CURSO";
    protected final String TextoGestionarUsuario  = "GESTIONAR USUARIO";
    protected final String cerrarSesionTexto  = "CerrarSesion";
    protected final String TextoGestionarCursoCommand  = "CURSO";
    protected final String TextoGestionarUsuarioCommand  = "USUARIO";
    protected final String cerrarSesionCommand  = "CERRAR";
    protected final String regresar = "Regresar";
    protected final String regresarCommand  = "Regresar";
    protected final String cancelar = "Cancelar";
    protected final String cancelarCommand  = "CANCELAR";

    private PanelBotonera botoneraCentro;
    private PanelBotonera botoneraSur;

    public PanelPrincipalAdmin(PanelManager panelManager) {
        super(panelManager);
//        crearBotonera();
        setUIComponentesBase();
    }

    @Override
    protected void agregarBotonera(PanelBotonera panelBotonera) {
        botoneraSur = new PanelBotonera();
        botoneraSur.agregarBoton(regresar,regresarCommand);
        botoneraSur.agregarBoton(cancelar,cancelarCommand);
        add(botoneraSur,BorderLayout.SOUTH);

        botoneraCentro = new PanelBotonera();
        botoneraCentro.agregarBoton(TextoGestionarCurso,TextoGestionarCursoCommand);
        botoneraCentro.agregarBoton(TextoGestionarUsuario,TextoGestionarUsuarioCommand);
        add(botoneraCentro,BorderLayout.CENTER);
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
        agregarBotonera(botoneraCentro);

        botoneraSur = new PanelBotonera();
        agregarBotonera(botoneraSur);

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

//    @Override
//    public void crearBotonera() {
//        botoneraSur = new PanelBotonera();
//        botoneraSur.agregarBoton(regresar,regresarCommand);
//        botoneraSur.agregarBoton(cancelar,cancelarCommand);
//
//        System.out.println("Botonera inferior creada: " + botoneraSur);
//        setBotonera(botoneraSur);
//
//        botoneraCentro = new PanelBotonera();
//        botoneraCentro.agregarBoton(TextoGestionarCurso,TextoGestionarCursoCommand);
//        botoneraCentro.agregarBoton(TextoGestionarUsuario,TextoGestionarUsuarioCommand);
//        System.out.println("Botonera centro creada: " + botoneraCentro);
//
//    }

    public void setListener(ActionListener listener) {
        botoneraCentro.agregarListener(listener);
        botoneraSur.agregarListener(listener);
    }
}
