package presentacion;

import datos.Excepcion.PanelException;

public class SystemUI {
    private PanelManager panelManager;
    private FrameInicio frameInicio;

    public SystemUI() {
        this.panelManager = new PanelManager();
        this.frameInicio = new FrameInicio(panelManager);
    }

    public void iniciar() throws PanelException {
        panelManager.iniciar();
        panelManager.showFrame();
    }
}
