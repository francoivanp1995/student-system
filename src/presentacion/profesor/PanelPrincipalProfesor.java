package presentacion.profesor;

import presentacion.PanelManager;
import presentacion.abstracto.PanelBase;

import javax.swing.*;
import java.awt.event.ActionListener;

public class PanelPrincipalProfesor extends PanelBase {

    public PanelPrincipalProfesor(PanelManager panelManager) {
        super(panelManager);

    }

    @Override
    protected void agregarBotonABotonera() {

    }

    @Override
    protected JPanel panelCentral() {
        return null;
    }

    @Override
    protected void setListener(ActionListener listener) {

    }

    @Override
    protected void setUIComponentesBase() {

    }

    @Override
    protected JPanel panelSuperior() {
        return null;
    }
}
