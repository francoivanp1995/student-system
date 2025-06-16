package presentacion;

import datos.Excepcion.PanelException;

public class UIIniciador {
    private PanelManager panelManager;
    private FrameInicio frameInicio;

    public UIIniciador() {
        this.panelManager = new PanelManager();
        this.frameInicio = new FrameInicio(panelManager);
    }

    public void iniciarFrame() throws PanelException {
        panelManager.iniciar();
        panelManager.showFrame();
    }
}
