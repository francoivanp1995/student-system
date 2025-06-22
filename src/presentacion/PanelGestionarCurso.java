package presentacion;

import presentacion.abstracto.PanelBase;

import javax.swing.*;
import java.awt.*;

public class PanelGestionarCurso extends PanelBase {

    private PanelManager panelManager;

    public PanelGestionarCurso(PanelManager panelManager){
        this.panelManager = panelManager;

    }

    @Override
    protected void agregarBotonera(PanelBotonera panelBotonera) {

    }

    @Override
    protected void setUIComponentesBase() {

    }

    @Override
    protected JPanel panelSuperior() {
        JPanel tituloPanel = new JPanel();
        JLabel nombreTitulo = new JLabel("GESTION CURSOS");
        tituloPanel.add(nombreTitulo);
        return tituloPanel;
    }

    @Override
    protected JPanel panelCentral() {
        JPanel formularioPanel = new JPanel(new FlowLayout());
        JPanel panelBasico = new JPanel();
        JButton boton1 = new JButton("Hola");
        JPanel aux = new JPanel();
        aux.setBorder(BorderFactory.createLineBorder(Color.RED));
        aux.add(boton1);
        panelBasico.setLayout(new FlowLayout());
        panelBasico.add(aux);
        JButton boton2 = new JButton("como");
        panelBasico.add(boton2);
        JButton boton3 = new JButton("va?");
        panelBasico.add(boton3);

        return formularioPanel;

    }

}
