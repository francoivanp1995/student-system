package presentacion;

import presentacion.abstracto.PanelBase;

import javax.swing.*;

public class PanelGestionUsuario extends PanelBase {

    private static PanelManager panelManager;

    public PanelGestionUsuario(PanelManager panelManager){
        this.panelManager = panelManager;
    }

    @Override
    protected void agregarBotonABotonera() {

    }

    @Override
    protected void setUIComponentesBase() {

    }

    @Override
    protected JPanel panelSuperior() {
        return null;
    }

    @Override
    protected JPanel panelCentral() {
        return null;
    }
    //Todo
}
