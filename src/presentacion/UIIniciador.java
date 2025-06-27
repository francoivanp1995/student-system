package presentacion;

import datos.Excepcion.PanelException;
import datos.Excepcion.UIException;

public class UIIniciador {
    private PanelManager panelManager;
    private FramePrincipal frameInicio;
    private ControladorPanelInicio controladorPanelInicio;
    private PanelInicio panelInicio;

    public UIIniciador() {
        this.panelManager = new PanelManager();
        this.frameInicio = new FramePrincipal(panelManager);
    }

    public void iniciarFrame() throws UIException {
        try {
            panelManager.iniciar();
            panelInicio = panelManager.getPanelInicio();
            controladorPanelInicio = new ControladorPanelInicio(panelInicio,panelManager);
            panelInicio.setListener(controladorPanelInicio);
            panelManager.showFrame();
        } catch (PanelException e) {
            throw new UIException(e);
        }
    }
}
